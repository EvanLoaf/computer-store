package com.gmail.evanloafakahaitao.computer.store.services.converter.entity;

import com.gmail.evanloafakahaitao.computer.store.dao.model.Discount;
import com.gmail.evanloafakahaitao.computer.store.dao.model.Profile;
import com.gmail.evanloafakahaitao.computer.store.dao.model.User;
import com.gmail.evanloafakahaitao.computer.store.services.converter.EntityConverter;
import com.gmail.evanloafakahaitao.computer.store.services.dto.DiscountDTO;
import com.gmail.evanloafakahaitao.computer.store.services.dto.OrderUserDTO;
import com.gmail.evanloafakahaitao.computer.store.services.dto.ProfileDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component("orderUserEntityConverter")
public class OrderUserEntityConverter implements EntityConverter<OrderUserDTO, User> {

    private final EntityConverter<DiscountDTO, Discount> discountEntityConverter;
    private final EntityConverter<ProfileDTO, Profile> profileEntityConverter;

    @Autowired
    public OrderUserEntityConverter(
            @Qualifier("discountEntityConverter") EntityConverter<DiscountDTO, Discount> discountEntityConverter,
            @Qualifier("profileEntityConverter") EntityConverter<ProfileDTO, Profile> profileEntityConverter
    ) {
        this.discountEntityConverter = discountEntityConverter;
        this.profileEntityConverter = profileEntityConverter;
    }

    @Override
    public User toEntity(OrderUserDTO dto) {
        User user = new User();
        if (dto.getEmail() != null) {
            user.setEmail(dto.getEmail());
        }
        if (dto.getProfile() != null) {
            user.setProfile(
                    profileEntityConverter.toEntity(dto.getProfile())
            );
        }
        if (dto.getDiscount() != null) {
            user.setDiscount(
                    discountEntityConverter.toEntity(dto.getDiscount())
            );
        }
        return user;
    }
}
