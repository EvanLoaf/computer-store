package com.gmail.evanloafakahaitao.computer.store.services.converter.entity;

import com.gmail.evanloafakahaitao.computer.store.dao.model.Comment;
import com.gmail.evanloafakahaitao.computer.store.services.converter.EntityConverter;
import com.gmail.evanloafakahaitao.computer.store.services.dto.CommentDTO;

public class CommentEntityConverter implements EntityConverter<CommentDTO, Comment> {

    @Override
    public Comment toEntity(CommentDTO dto) {
        Comment comment = new Comment();
        if (dto.getId() != null) {
            comment.setId(dto.getId());
        }
        if (dto.getContent() != null) {
            comment.setContent(dto.getContent());
        }
        return null;
    }
}
