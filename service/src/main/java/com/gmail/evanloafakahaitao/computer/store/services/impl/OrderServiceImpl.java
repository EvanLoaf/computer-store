package com.gmail.evanloafakahaitao.computer.store.services.impl;

import com.gmail.evanloafakahaitao.computer.store.dao.ItemDao;
import com.gmail.evanloafakahaitao.computer.store.dao.OrderDao;
import com.gmail.evanloafakahaitao.computer.store.dao.UserDao;
import com.gmail.evanloafakahaitao.computer.store.dao.model.*;
import com.gmail.evanloafakahaitao.computer.store.services.OrderService;
import com.gmail.evanloafakahaitao.computer.store.services.converters.DTOConverter;
import com.gmail.evanloafakahaitao.computer.store.services.converters.EntityConverter;
import com.gmail.evanloafakahaitao.computer.store.services.dto.NewOrderDTO;
import com.gmail.evanloafakahaitao.computer.store.services.dto.OrderDTO;
import com.gmail.evanloafakahaitao.computer.store.services.dto.SimpleOrderDTO;
import com.gmail.evanloafakahaitao.computer.store.services.dto.SimpleUserDTO;
import com.gmail.evanloafakahaitao.computer.store.services.util.CurrentUserUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

@Service
@Transactional
public class OrderServiceImpl implements OrderService {

    private static final Logger logger = LogManager.getLogger(OrderServiceImpl.class);

    private final OrderDao orderDao;
    private final ItemDao itemDao;
    private final UserDao userDao;
    private final EntityConverter<OrderDTO, Order> orderEntityConverter;
    private final EntityConverter<NewOrderDTO, Order> newOrderEntityConverter;
    private final DTOConverter<OrderDTO, Order> orderDTOConverter;
    private final DTOConverter<SimpleOrderDTO, Order> simpleOrderDTOConverter;

    @Autowired
    public OrderServiceImpl(
            OrderDao orderDao,
            ItemDao itemDao,
            UserDao userDao,
            @Qualifier("orderEntityConverter") EntityConverter<OrderDTO, Order> orderEntityConverter,
            @Qualifier("newOrderEntityConverter") EntityConverter<NewOrderDTO, Order> newOrderEntityConverter,
            @Qualifier("orderDTOConverter") DTOConverter<OrderDTO, Order> orderDTOConverter,
            @Qualifier("simpleOrderDTOConverter") DTOConverter<SimpleOrderDTO, Order> simpleOrderDTOConverter
    ) {
        this.orderDao = orderDao;
        this.itemDao = itemDao;
        this.userDao = userDao;
        this.orderEntityConverter = orderEntityConverter;
        this.newOrderEntityConverter = newOrderEntityConverter;
        this.orderDTOConverter = orderDTOConverter;
        this.simpleOrderDTOConverter = simpleOrderDTOConverter;
    }

    @Override
    public SimpleOrderDTO save(NewOrderDTO orderDTO) {
        logger.info("Saving Order");
        logger.debug(
                "Saving Order for Item {} from User {}",
                orderDTO.getItem().getVendorCode(),
                orderDTO.getUser().getEmail()
        );
        User user = userDao.findByEmail(orderDTO.getUser().getEmail());
        Item item = itemDao.findByVendorCode(orderDTO.getItem().getVendorCode());
        Order order = new Order();
        order.setQuantity(orderDTO.getQuantity());
        order.setCreated(LocalDateTime.now());
        order.setStatus(OrderStatusEnum.NEW);
        order.getId().setOrderCode(UUID.randomUUID().toString());
        BigDecimal quantity = BigDecimal.valueOf(order.getQuantity());
        BigDecimal itemDiscountFactor = BigDecimal.valueOf(1).setScale(5, RoundingMode.CEILING);
        if (!item.getDiscounts().isEmpty()) {
            for (Discount discount : item.getDiscounts()) {
                itemDiscountFactor = itemDiscountFactor
                        .multiply(BigDecimal.valueOf(100L - discount.getPercent()))
                        .divide(BigDecimal.valueOf(100), RoundingMode.CEILING);
            }
        }
        BigDecimal userDiscountFactor = BigDecimal.valueOf(1).setScale(5, RoundingMode.CEILING);
        if (user.getDiscount() != null) {
            userDiscountFactor = userDiscountFactor
                    .multiply(BigDecimal.valueOf(100L - user.getDiscount().getPercent()))
                    .divide(BigDecimal.valueOf(100), RoundingMode.CEILING);
        }
        order.setTotalPrice(
                item.getPrice()
                        .multiply(itemDiscountFactor)
                        .multiply(userDiscountFactor)
                        .multiply(quantity)
        );
        order.setUser(user);
        order.setItem(item);
        orderDao.create(order);
        return simpleOrderDTOConverter.toDto(order);
    }

    @Override
    @Transactional(readOnly = true)
    public List<SimpleOrderDTO> findByCurrentUser(Integer firstResult, Integer maxResults) {
        logger.info("Retrieving Orders by User Id");
        logger.debug("Retrieving Orders by User Id : {}", CurrentUserUtil.getCurrentId());
        List<Order> orders = orderDao.findByUserId(
                CurrentUserUtil.getCurrentId(),
                firstResult,
                maxResults
        );
        if (!orders.isEmpty()) {
            return simpleOrderDTOConverter.toDTOList(orders);
        } else return Collections.emptyList();
    }

    @Override
    public SimpleOrderDTO update(SimpleOrderDTO simpleOrderDTO) {
        logger.info("Updating Order status");
        logger.debug(
                "Updating Order {} status to {}",
                simpleOrderDTO.getOrderCode(),
                simpleOrderDTO.getStatus()
        );
        if (simpleOrderDTO.getStatus() != null) {
            Order order = orderDao.findByOrderCode(simpleOrderDTO.getOrderCode());
            order.setStatus(OrderStatusEnum.getStatus(simpleOrderDTO.getStatus().toString()));
            orderDao.update(order);
            return simpleOrderDTOConverter.toDto(order);
        } else {
            return new SimpleOrderDTO();
        }
    }

    @Override
    public void deleteByOrderCode(SimpleOrderDTO orderDTO) {
        logger.info("Deleting Order by order code");
        logger.debug("Deleting Order by order code : {}", orderDTO.getOrderCode());
        orderDao.deleteByOrderCode(orderDTO.getOrderCode());
    }

    @Override
    @Transactional(readOnly = true)
    public List<OrderDTO> findAll(Integer firstResult, Integer maxResults) {
        logger.info("Retrieving Orders");
        List<Order> orders = orderDao.findAll(firstResult, maxResults);
        logger.debug("Retrieved Orders : {}", orders);
        return orderDTOConverter.toDTOList(orders);
    }

    @Override
    @Transactional(readOnly = true)
    public Long countAll() {
        logger.info("Counting all Orders");
        return orderDao.countAll();
    }

    @Override
    @Transactional
    public Long countAllByCurrentUser() {
        logger.info("Counting all Orders from User");
        logger.debug("Counting all Orders from User : {}", CurrentUserUtil.getCurrentId());
        return orderDao.countAllByUserId(CurrentUserUtil.getCurrentId());
    }
}
