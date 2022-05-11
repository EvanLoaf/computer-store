package com.gmail.evanloafakahaitao.computer.store.services.converters.dto;

import com.gmail.evanloafakahaitao.computer.store.dao.model.Item;
import com.gmail.evanloafakahaitao.computer.store.dao.model.Order;
import com.gmail.evanloafakahaitao.computer.store.services.converters.DTOConverter;
import com.gmail.evanloafakahaitao.computer.store.services.dto.OrderStatusEnum;
import com.gmail.evanloafakahaitao.computer.store.services.dto.SimpleItemDTO;
import com.gmail.evanloafakahaitao.computer.store.services.dto.SimpleOrderDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.math.RoundingMode;

@Component("simpleOrderDTOConverter")
public class SimpleOrderDTOConverter implements DTOConverter<SimpleOrderDTO, Order> {

    private final DTOConverter<SimpleItemDTO, Item> simpleItemDTOConverter;

    @Autowired
    public SimpleOrderDTOConverter(
            @Qualifier("simpleItemDTOConverter") DTOConverter<SimpleItemDTO, Item> simpleItemDTOConverter
    ) {
        this.simpleItemDTOConverter = simpleItemDTOConverter;
    }

    @Override
    public SimpleOrderDTO toDto(Order entity) {
        SimpleOrderDTO order = new SimpleOrderDTO();
        order.setOrderCode(entity.getId().getOrderCode());
        order.setCreated(entity.getCreated());
        order.setQuantity(entity.getQuantity());
        order.setTotalPrice(entity.getTotalPrice().setScale(2, RoundingMode.CEILING));
        order.setStatus(
                OrderStatusEnum.getStatus(entity.getStatus().toString())
        );
        order.setItem(
                simpleItemDTOConverter.toDto(entity.getItem())
        );
        return order;
    }
}
