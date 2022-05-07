package com.gmail.evanloafakahaitao.computer.store.services.converters.entity;

import com.gmail.evanloafakahaitao.computer.store.dao.model.Item;
import com.gmail.evanloafakahaitao.computer.store.services.converters.EntityConverter;
import com.gmail.evanloafakahaitao.computer.store.services.dto.SimpleItemDTO;
import org.springframework.stereotype.Component;

@Component("simpleItemEntityConverter")
public class SimpleItemEntityConverter implements EntityConverter<SimpleItemDTO, Item> {

    @Override
    public Item toEntity(SimpleItemDTO dto) {
        Item item = new Item();
        if (dto.getId() != null) {
            item.setId(dto.getId());
        }
        if (dto.getName() != null) {
            item.setName(dto.getName());
        }
        if (dto.getVendorCode() != null) {
            item.setVendorCode(dto.getVendorCode());
        }
        return item;
    }
}
