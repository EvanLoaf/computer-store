package com.gmail.evanloafakahaitao.computer.store.services.impl;

import com.gmail.evanloafakahaitao.computer.store.dao.CommentDao;
import com.gmail.evanloafakahaitao.computer.store.dao.UserDao;
import com.gmail.evanloafakahaitao.computer.store.dao.model.Comment;
import com.gmail.evanloafakahaitao.computer.store.dao.model.User;
import com.gmail.evanloafakahaitao.computer.store.services.CommentService;
import com.gmail.evanloafakahaitao.computer.store.services.converters.DTOConverter;
import com.gmail.evanloafakahaitao.computer.store.services.converters.EntityConverter;
import com.gmail.evanloafakahaitao.computer.store.services.dto.CommentDTO;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class CommentServiceImpl implements CommentService {

    private static final Logger logger = LogManager.getLogger(CommentServiceImpl.class);

    private final CommentDao commentDao;
    private final UserDao userDao;
    private final EntityConverter<CommentDTO, Comment> commentEntityConverter;
    private final DTOConverter<CommentDTO, Comment> commentDTOConverter;

    @Autowired
    public CommentServiceImpl(
            CommentDao commentDao,
            UserDao userDao,
            @Qualifier("commentEntityConverter") EntityConverter<CommentDTO, Comment> commentEntityConverter,
            @Qualifier("commentDTOConverter") DTOConverter<CommentDTO, Comment> commentDTOConverter
    ) {
        this.commentDao = commentDao;
        this.userDao = userDao;
        this.commentEntityConverter = commentEntityConverter;
        this.commentDTOConverter = commentDTOConverter;
    }

    @Override
    public CommentDTO save(CommentDTO commentDTO) {
        Session session = commentDao.getCurrentSession();
        try {
            Transaction transaction = session.getTransaction();
            if (!transaction.isActive()) {
                session.beginTransaction();
            }
            Comment comment = commentEntityConverter.toEntity(commentDTO);
            User user = userDao.findOne(commentDTO.getUser().getId());
            comment.setUser(user);
            comment.setCreated(LocalDateTime.now());
            commentDao.create(comment);
            CommentDTO savedComment = commentDTOConverter.toDto(comment);
            transaction.commit();
            return savedComment;
        } catch (Exception e) {
            if (session.getTransaction().isActive()) {
                session.getTransaction().rollback();
            }
            logger.error("Failed to save Comment", e);
        }
        return null;
    }

    @Override
    public void deleteById(CommentDTO commentDTO) {
        Session session = commentDao.getCurrentSession();
        try {
            Transaction transaction = session.getTransaction();
            if (!transaction.isActive()) {
                session.beginTransaction();
            }
            commentDao.deleteById(commentDTO.getId());
            transaction.commit();
        } catch (Exception e) {
            if (session.getTransaction().isActive()) {
                session.getTransaction().rollback();
            }
            logger.error("Failed to delete Comment by id", e);
        }
    }
}
