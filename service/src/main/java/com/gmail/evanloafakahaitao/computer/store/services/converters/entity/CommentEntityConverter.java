package com.gmail.evanloafakahaitao.computer.store.services.converters.entity;

import com.gmail.evanloafakahaitao.computer.store.dao.model.Comment;
import com.gmail.evanloafakahaitao.computer.store.services.converters.EntityConverter;
import com.gmail.evanloafakahaitao.computer.store.services.dto.CommentDTO;
import org.springframework.stereotype.Component;

@Component("commentEntityConverter")
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
