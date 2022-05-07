package com.gmail.evanloafakahaitao.computer.store.services.converter.dto;

import com.gmail.evanloafakahaitao.computer.store.dao.model.Discount;
import com.gmail.evanloafakahaitao.computer.store.dao.model.Item;
import com.gmail.evanloafakahaitao.computer.store.services.converter.DTOConverter;
import com.gmail.evanloafakahaitao.computer.store.services.dto.DiscountDTO;
import com.gmail.evanloafakahaitao.computer.store.services.dto.ItemDTO;

import java.math.RoundingMode;

public class ItemDTOConverter implements DTOConverter<ItemDTO, Item> {

    private final DTOConverter<DiscountDTO, Discount> discountDTOConverter = new DiscountDTOConverter();

    @Override
    public ItemDTO toDto(Item entity) {
        ItemDTO item = new ItemDTO();
        if (entity != null) {
            item.setId(entity.getId());
            item.setVendorCode(entity.getVendorCode());
            item.setName(entity.getName());
            item.setPrice(entity.getPrice().setScale(2, RoundingMode.CEILING));
            item.setDescription(entity.getDescription());
            if (!entity.getDiscounts().isEmpty()) {
                item.setDiscounts(
                        discountDTOConverter.toDTOSet(entity.getDiscounts())
                );
            }
        }
        return item;
    }
}
