package com.gmail.evanloafakahaitao.computer.store.services.converters.dto;

import com.gmail.evanloafakahaitao.computer.store.dao.model.BusinessCard;
import com.gmail.evanloafakahaitao.computer.store.services.converters.DTOConverter;
import com.gmail.evanloafakahaitao.computer.store.services.dto.BusinessCardDTO;
import org.springframework.stereotype.Component;

@Component("businessCardDTOConverter")
public class BusinessCardDTOConverter implements DTOConverter<BusinessCardDTO, BusinessCard> {

    @Override
    public BusinessCardDTO toDto(BusinessCard entity) {
        BusinessCardDTO businessCard = new BusinessCardDTO();
        businessCard.setId(entity.getId());
        businessCard.setName(entity.getName());
        businessCard.setTitle(entity.getTitle());
        businessCard.setPhoneNumber(entity.getPhoneNumber());
        return businessCard;
    }
}
