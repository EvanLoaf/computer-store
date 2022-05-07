package com.gmail.evanloafakahaitao.computer.store.services.converter.entity;

import com.gmail.evanloafakahaitao.computer.store.dao.model.Comment;
import com.gmail.evanloafakahaitao.computer.store.dao.model.News;
import com.gmail.evanloafakahaitao.computer.store.dao.model.User;
import com.gmail.evanloafakahaitao.computer.store.services.converter.EntityConverter;
import com.gmail.evanloafakahaitao.computer.store.services.dto.CommentDTO;
import com.gmail.evanloafakahaitao.computer.store.services.dto.NewsDTO;
import com.gmail.evanloafakahaitao.computer.store.services.dto.SimpleUserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component("newsEntityConverter")
public class NewsEntityConverter implements EntityConverter<NewsDTO, News> {

    private final EntityConverter<CommentDTO, Comment> commentEntityConverter;
    private final EntityConverter<SimpleUserDTO, User> simpleUserEntityConverter;

    @Autowired
    public NewsEntityConverter(
            @Qualifier("commentEntityConverter") EntityConverter<CommentDTO, Comment> commentEntityConverter,
            @Qualifier("simpleUserEntityConverter") EntityConverter<SimpleUserDTO, User> simpleUserEntityConverter
    ) {
        this.commentEntityConverter = commentEntityConverter;
        this.simpleUserEntityConverter = simpleUserEntityConverter;
    }

    @Override
    public News toEntity(NewsDTO dto) {
        News news = new News();
        if (dto.getId() != null) {
            news.setId(dto.getId());
        }
        if (dto.getContent() != null) {
            news.setContent(dto.getContent());
        }
        if (dto.getTitle() != null) {
            news.setTitle(dto.getTitle());
        }
        if (dto.getUser() != null) {
            news.setUser(
                    simpleUserEntityConverter.toEntity(dto.getUser())
            );
        }
        if (dto.getComments() != null) {
            news.setComments(
                    commentEntityConverter.toEntitySet(dto.getComments())
            );
        }
        return news;
    }
}
