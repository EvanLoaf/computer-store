package com.gmail.evanloafakahaitao.computer.store.services.converter.dto;

import com.gmail.evanloafakahaitao.computer.store.dao.model.Discount;
import com.gmail.evanloafakahaitao.computer.store.dao.model.Profile;
import com.gmail.evanloafakahaitao.computer.store.dao.model.Role;
import com.gmail.evanloafakahaitao.computer.store.dao.model.User;
import com.gmail.evanloafakahaitao.computer.store.services.converter.DTOConverter;
import com.gmail.evanloafakahaitao.computer.store.services.dto.DiscountDTO;
import com.gmail.evanloafakahaitao.computer.store.services.dto.ProfileDTO;
import com.gmail.evanloafakahaitao.computer.store.services.dto.RoleDTO;
import com.gmail.evanloafakahaitao.computer.store.services.dto.UserDTO;

public class UserDTOConverter implements DTOConverter<UserDTO, User> {

    private final DTOConverter<RoleDTO, Role> roleDTOConverter = new RoleDTOConverter();
    private final DTOConverter<DiscountDTO, Discount> discountDTOConverter = new DiscountDTOConverter();
    private final DTOConverter<ProfileDTO, Profile> profileDTOConverter = new ProfileDTOConverter();

    @Override
    public UserDTO toDto(User entity) {
        UserDTO user = new UserDTO();
        user.setId(entity.getId());
        user.setFirstName(entity.getFirstName());
        user.setLastName(entity.getLastName());
        user.setEmail(entity.getEmail());
        user.setPassword(entity.getPassword());
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
        return user;
    }
}
