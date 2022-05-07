package com.gmail.evanloafakahaitao.computer.store.services.converter.dto;

import com.gmail.evanloafakahaitao.computer.store.dao.model.Item;
import com.gmail.evanloafakahaitao.computer.store.dao.model.Order;
import com.gmail.evanloafakahaitao.computer.store.dao.model.User;
import com.gmail.evanloafakahaitao.computer.store.services.converter.DTOConverter;
import com.gmail.evanloafakahaitao.computer.store.services.dto.ItemDTO;
import com.gmail.evanloafakahaitao.computer.store.services.dto.OrderDTO;
import com.gmail.evanloafakahaitao.computer.store.services.dto.OrderStatusEnum;
import com.gmail.evanloafakahaitao.computer.store.services.dto.OrderUserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.math.RoundingMode;
@Component("orderDTOConverter")
public class OrderDTOConverter implements DTOConverter<OrderDTO, Order> {

    private final DTOConverter<OrderUserDTO, User> orderUserDTOConverter;
    private final DTOConverter<ItemDTO, Item> itemDTOConverter;

    @Autowired
    public OrderDTOConverter(
            @Qualifier("orderUserDTOConverter") DTOConverter<OrderUserDTO, User> orderUserDTOConverter,
            @Qualifier("itemDTOConverter") DTOConverter<ItemDTO, Item> itemDTOConverter
    ) {
        this.orderUserDTOConverter = orderUserDTOConverter;
        this.itemDTOConverter = itemDTOConverter;
    }

    @Override
    public OrderDTO toDto(Order entity) {
        OrderDTO order = new OrderDTO();
        order.setOrderCode(entity.getId().getOrderCode());
        order.setCreated(entity.getCreated());
        order.setQuantity(entity.getQuantity());
        order.setTotalPrice(entity.getTotalPrice().setScale(2, RoundingMode.CEILING));
        order.setStatus(
                OrderStatusEnum.getStatus(entity.getStatus().toString())
        );
        order.setUser(
                orderUserDTOConverter.toDto(entity.getUser())
        );
        order.setItem(
                itemDTOConverter.toDto(entity.getItem())
        );
        return order;
    }
}
