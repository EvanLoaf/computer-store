package com.gmail.evanloafakahaitao.computer.store.services.converters.dto;

import com.gmail.evanloafakahaitao.computer.store.dao.model.Comment;
import com.gmail.evanloafakahaitao.computer.store.dao.model.News;
import com.gmail.evanloafakahaitao.computer.store.dao.model.User;
import com.gmail.evanloafakahaitao.computer.store.services.converters.DTOConverter;
import com.gmail.evanloafakahaitao.computer.store.services.dto.CommentDTO;
import com.gmail.evanloafakahaitao.computer.store.services.dto.NewsDTO;
import com.gmail.evanloafakahaitao.computer.store.services.dto.SimpleUserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component("newsDTOConverter")
public class NewsDTOConverter implements DTOConverter<NewsDTO, News> {

    private final DTOConverter<SimpleUserDTO, User> simpleUserDTOConverter;
    private final DTOConverter<CommentDTO, Comment> commentDTOConverter;

    @Autowired
    public NewsDTOConverter(
            @Qualifier("simpleUserDTOConverter") DTOConverter<SimpleUserDTO, User> simpleUserDTOConverter,
            @Qualifier("commentDTOConverter") DTOConverter<CommentDTO, Comment> commentDTOConverter
    ) {
        this.simpleUserDTOConverter = simpleUserDTOConverter;
        this.commentDTOConverter = commentDTOConverter;
    }

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
