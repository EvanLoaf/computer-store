package com.gmail.evanloafakahaitao.computer.store.services.impl;

import com.gmail.evanloafakahaitao.computer.store.dao.NewsDao;
import com.gmail.evanloafakahaitao.computer.store.dao.UserDao;
import com.gmail.evanloafakahaitao.computer.store.dao.model.Comment;
import com.gmail.evanloafakahaitao.computer.store.dao.model.News;
import com.gmail.evanloafakahaitao.computer.store.dao.model.User;
import com.gmail.evanloafakahaitao.computer.store.services.CommentService;
import com.gmail.evanloafakahaitao.computer.store.services.converters.DTOConverter;
import com.gmail.evanloafakahaitao.computer.store.services.converters.EntityConverter;
import com.gmail.evanloafakahaitao.computer.store.services.dto.CommentDTO;
import com.gmail.evanloafakahaitao.computer.store.services.dto.NewsDTO;
import com.gmail.evanloafakahaitao.computer.store.services.util.CurrentUserUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Iterator;

@Service
@Transactional
public class CommentServiceImpl implements CommentService {

    private static final Logger logger = LogManager.getLogger(CommentServiceImpl.class);

    private final NewsDao newsDao;
    private final UserDao userDao;
    private final EntityConverter<CommentDTO, Comment> commentEntityConverter;
    private final DTOConverter<CommentDTO, Comment> commentDTOConverter;

    @Autowired
    public CommentServiceImpl(
            NewsDao newsDao,
            UserDao userDao,
            @Qualifier("commentEntityConverter") EntityConverter<CommentDTO, Comment> commentEntityConverter,
            @Qualifier("commentDTOConverter") DTOConverter<CommentDTO, Comment> commentDTOConverter
    ) {
        this.newsDao = newsDao;
        this.userDao = userDao;
        this.commentEntityConverter = commentEntityConverter;
        this.commentDTOConverter = commentDTOConverter;
    }

    @Override
    public CommentDTO save(NewsDTO newsDTO) {
        logger.info("Saving comment for article : {}", newsDTO.getId());
        if (!newsDTO.getComments().isEmpty()) {
            News news = newsDao.findOne(newsDTO.getId());
            Comment comment = commentEntityConverter.toEntity(
                    newsDTO.getComments().iterator().next()
            );
            User user = userDao.findOne(CurrentUserUtil.getCurrentId());
            comment.setCreated(LocalDateTime.now());
            comment.setUser(user);
            comment.setDeleted(false);
            news.getComments().add(comment);
            logger.debug("Saving comment {} for article : {}", comment.getContent(), newsDTO.getId());
            newsDao.update(news);
            return commentDTOConverter.toDto(comment);
        }
        return null;
    }

    @Override
    public void deleteById(NewsDTO newsDTO) {
        logger.info("Deleting Comment by Id");
        if (!newsDTO.getComments().isEmpty()) {
            News news = newsDao.findOne(newsDTO.getId());
            CommentDTO commentDTO = newsDTO.getComments().iterator().next();
            Iterator<Comment> commentIterator = news.getComments().iterator();
            while (commentIterator.hasNext()) {
                Comment comment = commentIterator.next();
                if (comment.getId().equals(commentDTO.getId())) {
                    logger.debug("Deleting Comment by Id {}", commentDTO.getId());
                    commentIterator.remove();
                    break;
                }
            }
            newsDao.update(news);
        }
    }
}
