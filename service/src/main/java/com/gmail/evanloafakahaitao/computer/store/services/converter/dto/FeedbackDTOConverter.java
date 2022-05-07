package com.gmail.evanloafakahaitao.computer.store.services.converter.dto;

import com.gmail.evanloafakahaitao.computer.store.dao.model.Feedback;
import com.gmail.evanloafakahaitao.computer.store.dao.model.User;
import com.gmail.evanloafakahaitao.computer.store.services.converter.DTOConverter;
import com.gmail.evanloafakahaitao.computer.store.services.dto.FeedbackDTO;
import com.gmail.evanloafakahaitao.computer.store.services.dto.SimpleUserDTO;

public class FeedbackDTOConverter implements DTOConverter<FeedbackDTO, Feedback> {

    private final DTOConverter<SimpleUserDTO, User> simpleUserDTOConverter = new SimpleUserDTOConverter();

    @Override
    public FeedbackDTO toDto(Feedback entity) {
        FeedbackDTO feedback = new FeedbackDTO();
        feedback.setId(entity.getId());
        feedback.setMessage(entity.getMessage());
        feedback.setUser(
                simpleUserDTOConverter.toDto(entity.getUser())
        );
        return feedback;
    }
}
