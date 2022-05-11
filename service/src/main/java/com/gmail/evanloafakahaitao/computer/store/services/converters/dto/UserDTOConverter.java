package com.gmail.evanloafakahaitao.computer.store.services.converters.dto;

import com.gmail.evanloafakahaitao.computer.store.dao.model.*;
import com.gmail.evanloafakahaitao.computer.store.services.converters.DTOConverter;
import com.gmail.evanloafakahaitao.computer.store.services.dto.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component("userDTOConverter")
public class UserDTOConverter implements DTOConverter<UserDTO, User> {

    private final DTOConverter<RoleDTO, Role> roleDTOConverter;
    private final DTOConverter<DiscountDTO, Discount> discountDTOConverter;
    private final DTOConverter<ProfileDTO, Profile> profileDTOConverter;
    private final DTOConverter<BusinessCardDTO, BusinessCard> businessCardDTOConverter;

    @Autowired
    public UserDTOConverter(
            @Qualifier("roleDTOConverter") DTOConverter<RoleDTO, Role> roleDTOConverter,
            @Qualifier("discountDTOConverter") DTOConverter<DiscountDTO, Discount> discountDTOConverter,
            @Qualifier("profileDTOConverter") DTOConverter<ProfileDTO, Profile> profileDTOConverter,
            @Qualifier("businessCardDTOConverter") DTOConverter<BusinessCardDTO, BusinessCard> businessCardDTOConverter) {
        this.roleDTOConverter = roleDTOConverter;
        this.discountDTOConverter = discountDTOConverter;
        this.profileDTOConverter = profileDTOConverter;
        this.businessCardDTOConverter = businessCardDTOConverter;
    }

    @Override
    public UserDTO toDto(User entity) {
        UserDTO user = new UserDTO();
        user.setId(entity.getId());
        user.setFirstName(entity.getFirstName());
        user.setLastName(entity.getLastName());
        user.setEmail(entity.getEmail());
        user.setPassword(entity.getPassword());
        user.setIsDisabled(entity.getDisabled());
        user.setRole(
                roleDTOConverter.toDto(entity.getRole())
        );
        if (entity.getProfile() != null) {
            user.setProfile(
                    profileDTOConverter.toDto(entity.getProfile())
            );
        }
        if (entity.getDiscount() != null) {
            user.setDiscount(
                    discountDTOConverter.toDto(entity.getDiscount())
            );
        }
        if (!entity.getBusinessCards().isEmpty()) {
            user.setBusinessCards(
                    businessCardDTOConverter.toDTOSet(entity.getBusinessCards())
            );
        }
        return user;
    }
}
