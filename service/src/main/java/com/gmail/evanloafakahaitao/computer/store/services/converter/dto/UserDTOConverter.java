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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component("userDTOConverter")
public class UserDTOConverter implements DTOConverter<UserDTO, User> {

    private final DTOConverter<RoleDTO, Role> roleDTOConverter;
    private final DTOConverter<DiscountDTO, Discount> discountDTOConverter;
    private final DTOConverter<ProfileDTO, Profile> profileDTOConverter;

    @Autowired
    public UserDTOConverter(
            @Qualifier("roleDTOConverter") DTOConverter<RoleDTO, Role> roleDTOConverter,
            @Qualifier("discountDTOConverter") DTOConverter<DiscountDTO, Discount> discountDTOConverter,
            @Qualifier("profileDTOConverter") DTOConverter<ProfileDTO, Profile> profileDTOConverter
    ) {
        this.roleDTOConverter = roleDTOConverter;
        this.discountDTOConverter = discountDTOConverter;
        this.profileDTOConverter = profileDTOConverter;
    }

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
