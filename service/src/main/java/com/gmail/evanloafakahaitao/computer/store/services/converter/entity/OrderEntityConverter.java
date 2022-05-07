package com.gmail.evanloafakahaitao.computer.store.services.converter.entity;

import com.gmail.evanloafakahaitao.computer.store.dao.model.*;
import com.gmail.evanloafakahaitao.computer.store.services.converter.EntityConverter;
import com.gmail.evanloafakahaitao.computer.store.services.dto.ItemDTO;
import com.gmail.evanloafakahaitao.computer.store.services.dto.OrderDTO;
import com.gmail.evanloafakahaitao.computer.store.services.dto.OrderUserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component("orderEntityConverter")
public class OrderEntityConverter implements EntityConverter<OrderDTO, Order> {

    private final EntityConverter<OrderUserDTO, User> orderUserEntityConverter;
    private final EntityConverter<ItemDTO, Item> itemEntityConverter;

    @Autowired
    public OrderEntityConverter(
            @Qualifier("orderUserEntityConverter") EntityConverter<OrderUserDTO, User> orderUserEntityConverter,
            @Qualifier("itemEntityConverter") EntityConverter<ItemDTO, Item> itemEntityConverter
    ) {
        this.orderUserEntityConverter = orderUserEntityConverter;
        this.itemEntityConverter = itemEntityConverter;
    }

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
        if (dto.getUser() != null) {
            order.setUser(
                    orderUserEntityConverter.toEntity(dto.getUser())
            );
        }
        if (dto.getItem() != null) {
            order.setItem(
                    itemEntityConverter.toEntity(dto.getItem())
            );
        }
        return order;
    }
}
