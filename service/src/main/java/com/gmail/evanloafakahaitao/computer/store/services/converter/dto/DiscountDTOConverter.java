package com.gmail.evanloafakahaitao.computer.store.services.converter.dto;

import com.gmail.evanloafakahaitao.computer.store.dao.model.Discount;
import com.gmail.evanloafakahaitao.computer.store.services.converter.DTOConverter;
import com.gmail.evanloafakahaitao.computer.store.services.dto.DiscountDTO;

public class DiscountDTOConverter implements DTOConverter<DiscountDTO, Discount> {

    @Override
    public DiscountDTO toDto(Discount entity) {
        DiscountDTO discount = new DiscountDTO();
        discount.setId(entity.getId());
        discount.setName(entity.getName());
        discount.setPercent(entity.getPercent());
        discount.setFinishDate(entity.getFinishDate());
        return discount;
    }
}
