package com.gmail.evanloafakahaitao.computer.store.services.converter.dto;

import com.gmail.evanloafakahaitao.computer.store.dao.model.Profile;
import com.gmail.evanloafakahaitao.computer.store.services.converter.DTOConverter;
import com.gmail.evanloafakahaitao.computer.store.services.dto.ProfileDTO;

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
