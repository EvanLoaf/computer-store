package com.gmail.evanloafakahaitao.computer.store.services.converters.dto;

import com.gmail.evanloafakahaitao.computer.store.dao.model.Discount;
import com.gmail.evanloafakahaitao.computer.store.dao.model.Profile;
import com.gmail.evanloafakahaitao.computer.store.dao.model.User;
import com.gmail.evanloafakahaitao.computer.store.services.converters.DTOConverter;
import com.gmail.evanloafakahaitao.computer.store.services.dto.DiscountDTO;
import com.gmail.evanloafakahaitao.computer.store.services.dto.OrderUserDTO;
import com.gmail.evanloafakahaitao.computer.store.services.dto.ProfileDTO;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component("orderUserDTOConverter")
public class OrderUserDTOConverter implements DTOConverter<OrderUserDTO, User> {

    private final DTOConverter<DiscountDTO, Discount> discountDTOConverter;
    private final DTOConverter<ProfileDTO, Profile> profileDTOConverter;

    public OrderUserDTOConverter(
            @Qualifier("discountDTOConverter") DTOConverter<DiscountDTO, Discount> discountDTOConverter,
            @Qualifier("profileDTOConverter") DTOConverter<ProfileDTO, Profile> profileDTOConverter
    ) {
        this.discountDTOConverter = discountDTOConverter;
        this.profileDTOConverter = profileDTOConverter;
    }

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
