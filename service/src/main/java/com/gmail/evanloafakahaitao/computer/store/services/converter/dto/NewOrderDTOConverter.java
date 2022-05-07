package com.gmail.evanloafakahaitao.computer.store.services.converter.dto;

import com.gmail.evanloafakahaitao.computer.store.dao.model.Item;
import com.gmail.evanloafakahaitao.computer.store.dao.model.Order;
import com.gmail.evanloafakahaitao.computer.store.dao.model.User;
import com.gmail.evanloafakahaitao.computer.store.services.converter.DTOConverter;
import com.gmail.evanloafakahaitao.computer.store.services.dto.NewOrderDTO;
import com.gmail.evanloafakahaitao.computer.store.services.dto.SimpleItemDTO;
import com.gmail.evanloafakahaitao.computer.store.services.dto.SimpleUserDTO;

public class NewOrderDTOConverter implements DTOConverter<NewOrderDTO, Order> {

    private final DTOConverter<SimpleItemDTO, Item> simpleItemDTOConverter = new SimpleItemDTOConverter();
    private final DTOConverter<SimpleUserDTO, User> simpleUserDTOConverter = new SimpleUserDTOConverter();

    @Override
    public NewOrderDTO toDto(Order entity) {
        NewOrderDTO order = new NewOrderDTO();
        order.setQuantity(entity.getQuantity());
        order.setItem(
                simpleItemDTOConverter.toDto(entity.getItem())
        );
        order.setUser(
                simpleUserDTOConverter.toDto(entity.getUser())
        );
        return null;
    }
}
