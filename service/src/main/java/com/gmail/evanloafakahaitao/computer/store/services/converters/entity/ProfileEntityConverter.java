package com.gmail.evanloafakahaitao.computer.store.services.converters.entity;

import com.gmail.evanloafakahaitao.computer.store.dao.model.Profile;
import com.gmail.evanloafakahaitao.computer.store.services.converters.EntityConverter;
import com.gmail.evanloafakahaitao.computer.store.services.dto.ProfileDTO;
import org.springframework.stereotype.Component;

@Component("profileEntityConverter")
public class ProfileEntityConverter implements EntityConverter<ProfileDTO, Profile> {

    @Override
    public Profile toEntity(ProfileDTO dto) {
        Profile profile = new Profile();
        if (dto.getAddress() != null) {
            profile.setAddress(dto.getAddress());
        }
        if (dto.getPhoneNumber() != null) {
            profile.setPhoneNumber(dto.getPhoneNumber());
        }
        return profile;
    }
}
