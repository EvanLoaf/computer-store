package com.gmail.evanloafakahaitao.computer.store.services.converter.entity;

import com.gmail.evanloafakahaitao.computer.store.dao.model.Item;
import com.gmail.evanloafakahaitao.computer.store.services.converter.EntityConverter;
import com.gmail.evanloafakahaitao.computer.store.services.dto.SimpleItemDTO;

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
