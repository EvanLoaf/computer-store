package com.gmail.evanloafakahaitao.computer.store.services.converters.entity;

import com.gmail.evanloafakahaitao.computer.store.dao.model.Item;
import com.gmail.evanloafakahaitao.computer.store.dao.model.Order;
import com.gmail.evanloafakahaitao.computer.store.dao.model.OrderId;
import com.gmail.evanloafakahaitao.computer.store.dao.model.OrderStatusEnum;
import com.gmail.evanloafakahaitao.computer.store.services.converters.EntityConverter;
import com.gmail.evanloafakahaitao.computer.store.services.dto.SimpleItemDTO;
import com.gmail.evanloafakahaitao.computer.store.services.dto.SimpleOrderDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component("simpleOrderEntityConverter")
public class SimpleOrderEntityConverter implements EntityConverter<SimpleOrderDTO, Order> {

    private final EntityConverter<SimpleItemDTO, Item> simpleItemEntityConverter;

    @Autowired
    public SimpleOrderEntityConverter(
            @Qualifier("simpleItemEntityConverter") EntityConverter<SimpleItemDTO, Item> simpleItemEntityConverter
    ) {
        this.simpleItemEntityConverter = simpleItemEntityConverter;
    }

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
        if (dto.getItem() != null) {
            order.setItem(
                    simpleItemEntityConverter.toEntity(dto.getItem())
            );
        }
        return order;
    }
}
