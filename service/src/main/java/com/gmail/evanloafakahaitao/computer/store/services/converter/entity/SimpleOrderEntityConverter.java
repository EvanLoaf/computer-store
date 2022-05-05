package com.gmail.evanloafakahaitao.computer.store.services.converter.entity;

import com.gmail.evanloafakahaitao.computer.store.dao.model.Item;
import com.gmail.evanloafakahaitao.computer.store.dao.model.Order;
import com.gmail.evanloafakahaitao.computer.store.dao.model.OrderId;
import com.gmail.evanloafakahaitao.computer.store.dao.model.OrderStatusEnum;
import com.gmail.evanloafakahaitao.computer.store.services.converter.EntityConverter;
import com.gmail.evanloafakahaitao.computer.store.services.dto.SimpleItemDTO;
import com.gmail.evanloafakahaitao.computer.store.services.dto.SimpleOrderDTO;

public class SimpleOrderEntityConverter implements EntityConverter<SimpleOrderDTO, Order> {

    private final EntityConverter<SimpleItemDTO, Item> simpleItemEntityConverter = new SimpleItemEntityConverter();

    @Override
    public Order toEntity(SimpleOrderDTO dto) {
        Order order = new Order();
        if (dto.getOrderCode() != null) {
            OrderId orderId = new OrderId();
            orderId.setOrderCode(dto.getOrderCode());
            order.setId(orderId);
        }
        if (dto.getQuantity() != null) {
            order.setQuantity(dto.getQuantity());
        }
        if (dto.getTotalPrice() != null) {
            order.setTotalPrice(dto.getTotalPrice());
        }
        if (dto.getStatus() != null) {
            order.setStatus(
                    OrderStatusEnum.getStatus(dto.getStatus().toString())
            );
        }
        order.setItem(
                simpleItemEntityConverter.toEntity(dto.getItem())
        );
        return order;
    }
}
