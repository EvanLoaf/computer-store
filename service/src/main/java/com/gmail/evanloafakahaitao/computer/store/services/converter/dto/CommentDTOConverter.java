package com.gmail.evanloafakahaitao.computer.store.services.converter.dto;

import com.gmail.evanloafakahaitao.computer.store.dao.model.Comment;
import com.gmail.evanloafakahaitao.computer.store.dao.model.User;
import com.gmail.evanloafakahaitao.computer.store.services.converter.DTOConverter;
import com.gmail.evanloafakahaitao.computer.store.services.dto.CommentDTO;
import com.gmail.evanloafakahaitao.computer.store.services.dto.SimpleUserDTO;

import java.time.format.DateTimeFormatter;

public class CommentDTOConverter implements DTOConverter<CommentDTO, Comment> {

    private final DTOConverter<SimpleUserDTO, User> simpleUserDTOConverter = new SimpleUserDTOConverter();

    @Override
    public CommentDTO toDto(Comment entity) {
        CommentDTO comment = new CommentDTO();
        comment.setId(entity.getId());
        comment.setContent(entity.getContent());
        comment.setCreated(entity.getCreated());
        comment.setUser(
                simpleUserDTOConverter.toDto(entity.getUser())
        );
        return comment;
    }
}
