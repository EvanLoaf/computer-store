package com.gmail.evanloafakahaitao.computer.store.services.converters.dto;

import com.gmail.evanloafakahaitao.computer.store.dao.model.Profile;
import com.gmail.evanloafakahaitao.computer.store.services.converters.DTOConverter;
import com.gmail.evanloafakahaitao.computer.store.services.dto.ProfileDTO;
import org.springframework.stereotype.Component;

@Component("profileDTOConverter")
public class ProfileDTOConverter implements DTOConverter<ProfileDTO, Profile> {

    @Override
    public ProfileDTO toDto(Profile entity) {
        ProfileDTO profile = new ProfileDTO();
        if (entity.getAddress() != null) {
            profile.setAddress(entity.getAddress());
        }
        if (entity.getPhoneNumber() != null) {
            profile.setPhoneNumber(entity.getPhoneNumber());
        }
        return profile;
    }
}
