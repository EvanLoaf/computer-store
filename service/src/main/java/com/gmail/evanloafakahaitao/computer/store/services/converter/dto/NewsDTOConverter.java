package com.gmail.evanloafakahaitao.computer.store.services.converter.dto;

import com.gmail.evanloafakahaitao.computer.store.dao.model.Comment;
import com.gmail.evanloafakahaitao.computer.store.dao.model.News;
import com.gmail.evanloafakahaitao.computer.store.dao.model.User;
import com.gmail.evanloafakahaitao.computer.store.services.converter.DTOConverter;
import com.gmail.evanloafakahaitao.computer.store.services.dto.CommentDTO;
import com.gmail.evanloafakahaitao.computer.store.services.dto.NewsDTO;
import com.gmail.evanloafakahaitao.computer.store.services.dto.SimpleUserDTO;

public class NewsDTOConverter implements DTOConverter<NewsDTO, News> {

    private final DTOConverter<SimpleUserDTO, User> simpleUserDTOConverter = new SimpleUserDTOConverter();
    private final DTOConverter<CommentDTO, Comment> commentDTOConverter = new CommentDTOConverter();

    @Override
    public NewsDTO toDto(News entity) {
        NewsDTO news = new NewsDTO();
        news.setId(entity.getId());
        news.setTitle(entity.getTitle());
        news.setCreated(entity.getCreated());
        news.setContent(entity.getContent());
        news.setUser(
                simpleUserDTOConverter.toDto(entity.getUser())
        );
        if (!entity.getComments().isEmpty()) {
            news.setComments(
                    commentDTOConverter.toDTOSet(entity.getComments())
            );
        }
        return news;
    }
}
