package com.gmail.evanloafakahaitao.computer.store.services.converters.dto;

import com.gmail.evanloafakahaitao.computer.store.dao.model.Feedback;
import com.gmail.evanloafakahaitao.computer.store.dao.model.User;
import com.gmail.evanloafakahaitao.computer.store.services.converters.DTOConverter;
import com.gmail.evanloafakahaitao.computer.store.services.dto.FeedbackDTO;
import com.gmail.evanloafakahaitao.computer.store.services.dto.SimpleUserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component("feedbackDTOConverter")
public class FeedbackDTOConverter implements DTOConverter<FeedbackDTO, Feedback> {

    private final DTOConverter<SimpleUserDTO, User> simpleUserDTOConverter;

    @Autowired
    public FeedbackDTOConverter(
            @Qualifier("simpleUserDTOConverter") DTOConverter<SimpleUserDTO, User> simpleUserDTOConverter
    ) {
        this.simpleUserDTOConverter = simpleUserDTOConverter;
    }

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
