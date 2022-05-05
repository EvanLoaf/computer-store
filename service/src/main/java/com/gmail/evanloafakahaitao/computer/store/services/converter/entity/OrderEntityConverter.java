package com.gmail.evanloafakahaitao.computer.store.services.converter.entity;

import com.gmail.evanloafakahaitao.computer.store.dao.model.*;
import com.gmail.evanloafakahaitao.computer.store.services.converter.EntityConverter;
import com.gmail.evanloafakahaitao.computer.store.services.dto.ItemDTO;
import com.gmail.evanloafakahaitao.computer.store.services.dto.OrderDTO;
import com.gmail.evanloafakahaitao.computer.store.services.dto.OrderUserDTO;

public class OrderEntityConverter implements EntityConverter<OrderDTO, Order> {

    private final EntityConverter<OrderUserDTO, User> orderUserEntityConverter = new OrderUserEntityConverter();
    private final EntityConverter<ItemDTO, Item> itemEntityConverter = new ItemEntityConverter();

    @Override
    public Order toEntity(OrderDTO dto) {
        Order order = new Order();
        if (dto.getOrderCode() != null) {
            OrderId orderId = new OrderId();
            orderId.setOrderCode(dto.getOrderCode());
            order.setId(orderId);
        }
        if (dto.getQuantity() != null) {
            order.setQuantity(dto.getQuantity());
        }
        if (dto.getStatus() != null) {
            order.setStatus(
                    OrderStatusEnum.getStatus(dto.getStatus().toString())
            );
        }
        if (dto.getTotalPrice() != null) {
            order.setTotalPrice(dto.getTotalPrice());
        }
        order.setUser(
                orderUserEntityConverter.toEntity(dto.getUser())
        );
        order.setItem(
                itemEntityConverter.toEntity(dto.getItem())
        );
        return null;
    }
}
