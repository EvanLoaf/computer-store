package com.gmail.evanloafakahaitao.computer.store.services.converter.entity;

import com.gmail.evanloafakahaitao.computer.store.dao.model.Discount;
import com.gmail.evanloafakahaitao.computer.store.services.converter.EntityConverter;
import com.gmail.evanloafakahaitao.computer.store.services.dto.DiscountDTO;

public class DiscountEntityConverter implements EntityConverter<DiscountDTO, Discount> {

    @Override
    public Discount toEntity(DiscountDTO dto) {
        Discount discount = new Discount();
        if (dto.getId() != null) {
            discount.setId(discount.getId());
        }
        if (dto.getName() != null) {
            discount.setName(discount.getName());
        }
        if (discount.getPercent() != null) {
            discount.setPercent(discount.getPercent());
        }
        if (discount.getFinishDate() != null) {
            discount.setFinishDate(discount.getFinishDate());
        }
        return discount;
    }
}
