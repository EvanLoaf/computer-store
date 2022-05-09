package com.gmail.evanloafakahaitao.computer.store.services.converters.entity;

import com.gmail.evanloafakahaitao.computer.store.dao.model.BusinessCard;
import com.gmail.evanloafakahaitao.computer.store.services.converters.EntityConverter;
import com.gmail.evanloafakahaitao.computer.store.services.dto.BusinessCardDTO;
import org.springframework.stereotype.Component;

@Component("businessCardEntityConverter")
public class BusinessCardEntityConverter implements EntityConverter<BusinessCardDTO, BusinessCard> {

    @Override
    public BusinessCard toEntity(BusinessCardDTO dto) {
        BusinessCard businessCard = new BusinessCard();
        if (dto.getId() != null) {
            businessCard.setId(dto.getId());
        }
        if (dto.getTitle() != null) {
            businessCard.setTitle(dto.getTitle());
        }
        if (dto.getName() != null) {
            businessCard.setName(dto.getName());
        }
        if (dto.getPhoneNumber() != null) {
            businessCard.setPhoneNumber(dto.getPhoneNumber());
        }
        return businessCard;
    }
}
