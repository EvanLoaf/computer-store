package com.gmail.evanloafakahaitao.computer.store.services.converter.entity;

import com.gmail.evanloafakahaitao.computer.store.dao.model.Item;
import com.gmail.evanloafakahaitao.computer.store.dao.model.Order;
import com.gmail.evanloafakahaitao.computer.store.dao.model.User;
import com.gmail.evanloafakahaitao.computer.store.services.converter.EntityConverter;
import com.gmail.evanloafakahaitao.computer.store.services.dto.NewOrderDTO;
import com.gmail.evanloafakahaitao.computer.store.services.dto.SimpleItemDTO;
import com.gmail.evanloafakahaitao.computer.store.services.dto.SimpleUserDTO;

public class NewOrderEntityConverter implements EntityConverter<NewOrderDTO, Order> {

    private final EntityConverter<SimpleUserDTO, User> simpleUserEntityConverter = new SimpleUserEntityConverter();
    private final EntityConverter<SimpleItemDTO, Item> simpleItemEntityConverter = new SimpleItemEntityConverter();

    @Override
    public Order toEntity(NewOrderDTO dto) {
        Order order = new Order();
        if (dto.getQuantity() != null) {
            order.setQuantity(dto.getQuantity());
        }
        order.setUser(
                simpleUserEntityConverter.toEntity(dto.getUser())
        );
        order.setItem(
                simpleItemEntityConverter.toEntity(dto.getItem())
        );
        return order;
    }
}
