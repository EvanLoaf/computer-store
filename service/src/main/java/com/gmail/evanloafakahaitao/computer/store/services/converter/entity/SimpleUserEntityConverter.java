package com.gmail.evanloafakahaitao.computer.store.services.converter.entity;

import com.gmail.evanloafakahaitao.computer.store.dao.model.User;
import com.gmail.evanloafakahaitao.computer.store.services.converter.EntityConverter;
import com.gmail.evanloafakahaitao.computer.store.services.dto.SimpleUserDTO;

public class SimpleUserEntityConverter implements EntityConverter<SimpleUserDTO, User> {

    @Override
    public User toEntity(SimpleUserDTO dto) {
        User user = new User();
        if (dto.getId() != null) {
            user.setId(dto.getId());
        }
        if (dto.getEmail() != null) {
            user.setEmail(dto.getEmail());
        }
        return user;
    }
}
