package com.gmail.evanloafakahaitao.computer.store.services.converter.entity;

import com.gmail.evanloafakahaitao.computer.store.dao.model.Discount;
import com.gmail.evanloafakahaitao.computer.store.dao.model.Item;
import com.gmail.evanloafakahaitao.computer.store.services.converter.EntityConverter;
import com.gmail.evanloafakahaitao.computer.store.services.dto.DiscountDTO;
import com.gmail.evanloafakahaitao.computer.store.services.dto.ItemDTO;

public class ItemEntityConverter implements EntityConverter<ItemDTO, Item> {

    private final EntityConverter<DiscountDTO, Discount> discountEntityConverter = new DiscountEntityConverter();

    @Override
    public Item toEntity(ItemDTO dto) {
        Item item = new Item();
        if (dto.getId() != null) {
            item.setId(dto.getId());
        }
        if (dto.getVendorCode() != null) {
            item.setVendorCode(dto.getVendorCode());
        }
        if (dto.getName() != null) {
            item.setName(dto.getName());
        }
        if (dto.getPrice() != null) {
            item.setPrice(dto.getPrice());
        }
        if (dto.getDescription() != null) {
            item.setDescription(dto.getDescription());
        }
        if (!dto.getDiscounts().isEmpty()) {
            item.setDiscounts(
                    discountEntityConverter.toEntitySet(dto.getDiscounts())
            );
        }
        return item;
    }
}
