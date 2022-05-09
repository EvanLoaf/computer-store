package com.gmail.evanloafakahaitao.computer.store.services.converters.entity;

import com.gmail.evanloafakahaitao.computer.store.dao.model.Discount;
import com.gmail.evanloafakahaitao.computer.store.dao.model.Profile;
import com.gmail.evanloafakahaitao.computer.store.dao.model.Role;
import com.gmail.evanloafakahaitao.computer.store.dao.model.User;
import com.gmail.evanloafakahaitao.computer.store.services.converters.EntityConverter;
import com.gmail.evanloafakahaitao.computer.store.services.dto.DiscountDTO;
import com.gmail.evanloafakahaitao.computer.store.services.dto.ProfileDTO;
import com.gmail.evanloafakahaitao.computer.store.services.dto.RoleDTO;
import com.gmail.evanloafakahaitao.computer.store.services.dto.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component("userEntityConverter")
public class UserEntityConverter implements EntityConverter<UserDTO, User> {

    private final EntityConverter<RoleDTO, Role> roleEntityConverter;
    private final EntityConverter<ProfileDTO, Profile> profileEntityConverter;
    private final EntityConverter<DiscountDTO, Discount> discountEntityConverter;

    @Autowired
    public UserEntityConverter(
            @Qualifier("roleEntityConverter") EntityConverter<RoleDTO, Role> roleEntityConverter,
            @Qualifier("profileEntityConverter") EntityConverter<ProfileDTO, Profile> profileEntityConverter,
            @Qualifier("discountEntityConverter") EntityConverter<DiscountDTO, Discount> discountEntityConverter
    ) {
        this.roleEntityConverter = roleEntityConverter;
        this.profileEntityConverter = profileEntityConverter;
        this.discountEntityConverter = discountEntityConverter;
    }

    @Override
    public User toEntity(UserDTO dto) {
        User user = new User();
        if (dto.getEmail() != null) {
            user.setEmail(dto.getEmail());
        }
        if (dto.getPassword() != null) {
            user.setPassword(dto.getPassword());
        }
        if (dto.getFirstName() != null) {
            user.setFirstName(dto.getFirstName());
        }
        if (dto.getLastName() != null) {
            user.setLastName(dto.getLastName());
        }
        if (dto.getDiscount() != null) {
            user.setDiscount(
                    discountEntityConverter.toEntity(dto.getDiscount())
            );
        }
        if (dto.getRole() != null) {
            user.setRole(
                    roleEntityConverter.toEntity(dto.getRole())
            );
        }
        if (dto.getProfile() != null) {
            user.setProfile(
                    profileEntityConverter.toEntity(dto.getProfile())
            );
        }
        if (dto.getDisabled() != null) {
            user.setDisabled(dto.getDisabled());
        }
        return user;
    }
}
