package com.gmail.evanloafakahaitao.computer.store.services.converters.dto;

import com.gmail.evanloafakahaitao.computer.store.dao.model.Discount;
import com.gmail.evanloafakahaitao.computer.store.services.converters.DTOConverter;
import com.gmail.evanloafakahaitao.computer.store.services.dto.DiscountDTO;
import org.springframework.stereotype.Component;

@Component("discountDTOConverter")
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
