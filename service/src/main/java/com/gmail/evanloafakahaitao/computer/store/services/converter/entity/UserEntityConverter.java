package com.gmail.evanloafakahaitao.computer.store.services.converter.entity;

import com.gmail.evanloafakahaitao.computer.store.dao.model.Discount;
import com.gmail.evanloafakahaitao.computer.store.dao.model.Profile;
import com.gmail.evanloafakahaitao.computer.store.dao.model.Role;
import com.gmail.evanloafakahaitao.computer.store.dao.model.User;
import com.gmail.evanloafakahaitao.computer.store.services.converter.EntityConverter;
import com.gmail.evanloafakahaitao.computer.store.services.dto.DiscountDTO;
import com.gmail.evanloafakahaitao.computer.store.services.dto.ProfileDTO;
import com.gmail.evanloafakahaitao.computer.store.services.dto.RoleDTO;
import com.gmail.evanloafakahaitao.computer.store.services.dto.UserDTO;

public class UserEntityConverter implements EntityConverter<UserDTO, User> {

    private final EntityConverter<RoleDTO, Role> roleEntityConverter = new RoleEntityConverter();
    private final EntityConverter<ProfileDTO, Profile> profileEntityConverter = new ProfileEntityConverter();
    private final EntityConverter<DiscountDTO, Discount> discountEntityConverter = new DiscountEntityConverter();

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
        user.setRole(
                roleEntityConverter.toEntity(dto.getRole())
        );
        user.setProfile(
                profileEntityConverter.toEntity(dto.getProfile())
        );
        user.setDiscount(
                discountEntityConverter.toEntity(dto.getDiscount())
        );
        return user;
    }
}
