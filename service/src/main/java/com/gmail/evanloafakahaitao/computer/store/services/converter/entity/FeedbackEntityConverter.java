package com.gmail.evanloafakahaitao.computer.store.services.converter.entity;

import com.gmail.evanloafakahaitao.computer.store.dao.model.Feedback;
import com.gmail.evanloafakahaitao.computer.store.dao.model.User;
import com.gmail.evanloafakahaitao.computer.store.services.converter.EntityConverter;
import com.gmail.evanloafakahaitao.computer.store.services.dto.FeedbackDTO;
import com.gmail.evanloafakahaitao.computer.store.services.dto.SimpleUserDTO;

public class FeedbackEntityConverter implements EntityConverter<FeedbackDTO, Feedback> {

    private final EntityConverter<SimpleUserDTO, User> simpleUserEntityConverter = new SimpleUserEntityConverter();

    @Override
    public Feedback toEntity(FeedbackDTO dto) {
        Feedback feedback = new Feedback();
        if (dto.getId() != null) {
            feedback.setId(dto.getId());
        }
        if (dto.getMessage() != null) {
            feedback.setMessage(dto.getMessage());
        }
        if (dto.getUser() != null) {
            feedback.setUser(
                    simpleUserEntityConverter.toEntity(dto.getUser())
            );
        }
        return feedback;
    }
}
