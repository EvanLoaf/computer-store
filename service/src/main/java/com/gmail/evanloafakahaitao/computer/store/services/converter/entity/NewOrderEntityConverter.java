package com.gmail.evanloafakahaitao.computer.store.services.converter.entity;

import com.gmail.evanloafakahaitao.computer.store.dao.model.Item;
import com.gmail.evanloafakahaitao.computer.store.dao.model.Order;
import com.gmail.evanloafakahaitao.computer.store.dao.model.User;
import com.gmail.evanloafakahaitao.computer.store.services.converter.EntityConverter;
import com.gmail.evanloafakahaitao.computer.store.services.dto.NewOrderDTO;
import com.gmail.evanloafakahaitao.computer.store.services.dto.SimpleItemDTO;
import com.gmail.evanloafakahaitao.computer.store.services.dto.SimpleUserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component("newOrderEntityConverter")
public class NewOrderEntityConverter implements EntityConverter<NewOrderDTO, Order> {

    private final EntityConverter<SimpleUserDTO, User> simpleUserEntityConverter;
    private final EntityConverter<SimpleItemDTO, Item> simpleItemEntityConverter;

    @Autowired
    public NewOrderEntityConverter(
            @Qualifier("simpleUserEntityConverter") EntityConverter<SimpleUserDTO, User> simpleUserEntityConverter,
            @Qualifier("simpleItemEntityConverter") EntityConverter<SimpleItemDTO, Item> simpleItemEntityConverter
    ) {
        this.simpleUserEntityConverter = simpleUserEntityConverter;
        this.simpleItemEntityConverter = simpleItemEntityConverter;
    }

    @Override
    public Order toEntity(NewOrderDTO dto) {
        Order order = new Order();
        if (dto.getQuantity() != null) {
            order.setQuantity(dto.getQuantity());
        }
        if (dto.getUser() != null) {
            order.setUser(
                    simpleUserEntityConverter.toEntity(dto.getUser())
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
