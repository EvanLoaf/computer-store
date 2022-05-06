package com.gmail.evanloafakahaitao.computer.store.services.impl;

import com.gmail.evanloafakahaitao.computer.store.dao.ItemDao;
import com.gmail.evanloafakahaitao.computer.store.dao.OrderDao;
import com.gmail.evanloafakahaitao.computer.store.dao.UserDao;
import com.gmail.evanloafakahaitao.computer.store.dao.impl.ItemDaoImpl;
import com.gmail.evanloafakahaitao.computer.store.dao.impl.OrderDaoImpl;
import com.gmail.evanloafakahaitao.computer.store.dao.impl.UserDaoImpl;
import com.gmail.evanloafakahaitao.computer.store.dao.model.*;
import com.gmail.evanloafakahaitao.computer.store.services.OrderService;
import com.gmail.evanloafakahaitao.computer.store.services.converter.DTOConverter;
import com.gmail.evanloafakahaitao.computer.store.services.converter.EntityConverter;
import com.gmail.evanloafakahaitao.computer.store.services.converter.dto.OrderDTOConverter;
import com.gmail.evanloafakahaitao.computer.store.services.converter.dto.SimpleOrderDTOConverter;
import com.gmail.evanloafakahaitao.computer.store.services.converter.entity.NewOrderEntityConverter;
import com.gmail.evanloafakahaitao.computer.store.services.converter.entity.OrderEntityConverter;
import com.gmail.evanloafakahaitao.computer.store.services.dto.NewOrderDTO;
import com.gmail.evanloafakahaitao.computer.store.services.dto.OrderDTO;
import com.gmail.evanloafakahaitao.computer.store.services.dto.SimpleOrderDTO;
import com.gmail.evanloafakahaitao.computer.store.services.dto.SimpleUserDTO;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

public class OrderServiceImpl implements OrderService {

    private static final Logger logger = LogManager.getLogger(OrderServiceImpl.class);

    private final OrderDao orderDao = new OrderDaoImpl();
    private final ItemDao itemDao = new ItemDaoImpl();
    private final UserDao userDao = new UserDaoImpl();
    private final EntityConverter<OrderDTO, Order> orderEntityConverter = new OrderEntityConverter();
    private final EntityConverter<NewOrderDTO, Order> newOrderEntityConverter = new NewOrderEntityConverter();
    private final DTOConverter<OrderDTO, Order> orderDTOConverter = new OrderDTOConverter();
    private final DTOConverter<SimpleOrderDTO, Order> simpleOrderDTOConverter = new SimpleOrderDTOConverter();

    @Override
    public SimpleOrderDTO save(NewOrderDTO orderDTO) {
        Session session = orderDao.getCurrentSession();
        try {
            Transaction transaction = session.getTransaction();
            if (!transaction.isActive()) {
                session.beginTransaction();
            }
            User user = userDao.findByEmail(orderDTO.getUser().getEmail());
            Item item = itemDao.findByVendorCode(orderDTO.getItem().getVendorCode());
            Order order = newOrderEntityConverter.toEntity(orderDTO);
            order.setUser(user);
            order.setItem(item);
            OrderId orderId = new OrderId();
            //TODO mby set IDs to orderId too? or remove ordercode idea
            orderId.setOrderCode(
                    UUID.randomUUID().toString()
            );
            order.setId(orderId);
            order.setStatus(OrderStatusEnum.NEW);
            order.setQuantity(orderDTO.getQuantity());
            order.setCreated(LocalDateTime.now());
            //TODO discount impl
            BigDecimal quantity = BigDecimal.valueOf(orderDTO.getQuantity());
            order.setTotalPrice(
                    item.getPrice().multiply(quantity)
            );
            orderDao.create(order);
            SimpleOrderDTO savedOrder = simpleOrderDTOConverter.toDto(order);
            transaction.commit();
            return savedOrder;
        } catch (Exception e) {
            if (session.getTransaction().isActive()) {
                session.getTransaction().rollback();
            }
            logger.error("Failed to save order", e);
        }
        return null;
    }

    @Override
    public List<SimpleOrderDTO> findByUserId(SimpleUserDTO userDTO) {
        Session session = orderDao.getCurrentSession();
        try {
            Transaction transaction = session.getTransaction();
            if (!transaction.isActive()) {
                session.beginTransaction();
            }
            List<Order> orders = orderDao.findByUserId(userDTO.getId());
            List<SimpleOrderDTO> foundOrders = simpleOrderDTOConverter.toDTOList(orders);
            transaction.commit();
            return foundOrders;
        } catch (Exception e) {
            if (session.getTransaction().isActive()) {
                session.getTransaction().rollback();
            }
            logger.error("Failed to find Orders by user ID", e);
        }
        return Collections.emptyList();
    }

    @Override
    public void deleteByOrderCode(SimpleOrderDTO orderDTO) {
        Session session = orderDao.getCurrentSession();
        try {
            Transaction transaction = session.getTransaction();
            if (!transaction.isActive()) {
                session.beginTransaction();
            }
            orderDao.deleteByOrderCode(orderDTO.getOrderCode());
            transaction.commit();
        } catch (Exception e) {
            if (session.getTransaction().isActive()) {
                session.getTransaction().rollback();
            }
            logger.error("Failed to delete Order by order code", e);
        }
    }

    @Override
    public List<OrderDTO> findAll() {
        Session session = orderDao.getCurrentSession();
        try {
            Transaction transaction = session.getTransaction();
            if (!transaction.isActive()) {
                session.beginTransaction();
            }
            List<Order> orders = orderDao.findAll();
            List<OrderDTO> foundOrders = orderDTOConverter.toDTOList(orders);
            transaction.commit();
            return foundOrders;
        } catch (Exception e) {
            if (session.getTransaction().isActive()) {
                session.getTransaction().rollback();
            }
            logger.error("Failed to retrieve orders", e);
        }
        return Collections.emptyList();
    }
}
