package com.gmail.evanloafakahaitao.computer.store.services.converter.dto;

import com.gmail.evanloafakahaitao.computer.store.dao.model.Discount;
import com.gmail.evanloafakahaitao.computer.store.dao.model.Profile;
import com.gmail.evanloafakahaitao.computer.store.dao.model.User;
import com.gmail.evanloafakahaitao.computer.store.services.converter.DTOConverter;
import com.gmail.evanloafakahaitao.computer.store.services.dto.DiscountDTO;
import com.gmail.evanloafakahaitao.computer.store.services.dto.OrderUserDTO;
import com.gmail.evanloafakahaitao.computer.store.services.dto.ProfileDTO;

public class OrderUserDTOConverter implements DTOConverter<OrderUserDTO, User> {

    private final DTOConverter<DiscountDTO, Discount> discountDTOConverter = new DiscountDTOConverter();
    private final DTOConverter<ProfileDTO, Profile> profileDTOConverter = new ProfileDTOConverter();

    @Override
    public OrderUserDTO toDto(User entity) {
        OrderUserDTO user = new OrderUserDTO();
        user.setEmail(entity.getEmail());
        user.setName(entity.getFirstName() + " " + entity.getLastName());
        user.setProfile(
                profileDTOConverter.toDto(entity.getProfile())
        );
        if (entity.getDiscount() != null) {
            user.setDiscount(
                    discountDTOConverter.toDto(entity.getDiscount())
            );
        }
        return user;
    }
}
