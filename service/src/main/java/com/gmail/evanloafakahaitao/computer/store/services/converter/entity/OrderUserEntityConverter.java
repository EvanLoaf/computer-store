package com.gmail.evanloafakahaitao.computer.store.services.converter.entity;

import com.gmail.evanloafakahaitao.computer.store.dao.model.Discount;
import com.gmail.evanloafakahaitao.computer.store.dao.model.Profile;
import com.gmail.evanloafakahaitao.computer.store.dao.model.User;
import com.gmail.evanloafakahaitao.computer.store.services.converter.EntityConverter;
import com.gmail.evanloafakahaitao.computer.store.services.dto.DiscountDTO;
import com.gmail.evanloafakahaitao.computer.store.services.dto.OrderUserDTO;
import com.gmail.evanloafakahaitao.computer.store.services.dto.ProfileDTO;

public class OrderUserEntityConverter implements EntityConverter<OrderUserDTO, User> {

    private final EntityConverter<DiscountDTO, Discount> discountEntityConverter = new DiscountEntityConverter();
    private final EntityConverter<ProfileDTO, Profile> profileEntityConverter = new ProfileEntityConverter();

    @Override
    public User toEntity(OrderUserDTO dto) {
        User user = new User();
        if (dto.getEmail() != null) {
            user.setEmail(dto.getEmail());
        }
        user.setProfile(
                profileEntityConverter.toEntity(dto.getProfile())
        );
        user.setDiscount(
                discountEntityConverter.toEntity(dto.getDiscount())
        );
        return user;
    }
}
