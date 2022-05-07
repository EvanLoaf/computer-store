package com.gmail.evanloafakahaitao.computer.store.services.impl;

import com.gmail.evanloafakahaitao.computer.store.dao.FeedbackDao;
import com.gmail.evanloafakahaitao.computer.store.dao.UserDao;
import com.gmail.evanloafakahaitao.computer.store.dao.model.Feedback;
import com.gmail.evanloafakahaitao.computer.store.dao.model.User;
import com.gmail.evanloafakahaitao.computer.store.services.FeedbackService;
import com.gmail.evanloafakahaitao.computer.store.services.converters.DTOConverter;
import com.gmail.evanloafakahaitao.computer.store.services.converters.EntityConverter;
import com.gmail.evanloafakahaitao.computer.store.services.dto.FeedbackDTO;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class FeedbackServiceImpl implements FeedbackService {

    private static final Logger logger = LogManager.getLogger(FeedbackServiceImpl.class);

    private final FeedbackDao feedbackDao;
    private final UserDao userDao;
    private final EntityConverter<FeedbackDTO, Feedback> feedbackEntityConverter;
    private final DTOConverter<FeedbackDTO, Feedback> feedbackDTOConverter;

    @Autowired
    public FeedbackServiceImpl(
            FeedbackDao feedbackDao,
            UserDao userDao,
            @Qualifier("feedbackEntityConverter") EntityConverter<FeedbackDTO, Feedback> feedbackEntityConverter,
            @Qualifier("feedbackDTOConverter") DTOConverter<FeedbackDTO, Feedback> feedbackDTOConverter
    ) {
        this.feedbackDao = feedbackDao;
        this.userDao = userDao;
        this.feedbackEntityConverter = feedbackEntityConverter;
        this.feedbackDTOConverter = feedbackDTOConverter;
    }

    @Override
    public FeedbackDTO save(FeedbackDTO feedbackDTO) {
        Session session = feedbackDao.getCurrentSession();
        try {
            Transaction transaction = session.getTransaction();
            if (!transaction.isActive()) {
                session.beginTransaction();
            }
            Feedback feedback = feedbackEntityConverter.toEntity(feedbackDTO);
            User user = userDao.findByEmail(feedbackDTO.getUser().getEmail());
            feedback.setUser(user);
            feedbackDao.create(feedback);
            FeedbackDTO savedFeedback = feedbackDTOConverter.toDto(feedback);
            transaction.commit();
            return savedFeedback;
        } catch (Exception e) {
            if (session.getTransaction().isActive()) {
                session.getTransaction().rollback();
            }
            logger.error("Failed to save Feedback", e);
        }
        return null;
    }

    @Override
    public List<FeedbackDTO> findAll() {
        Session session = feedbackDao.getCurrentSession();
        try {
            Transaction transaction = session.getTransaction();
            if (!transaction.isActive()) {
                session.beginTransaction();
            }
            List<Feedback> feedbacks = feedbackDao.findAll();
            List<FeedbackDTO> feedbacksDTO = feedbackDTOConverter.toDTOList(feedbacks);
            transaction.commit();
            return feedbacksDTO;
        } catch (Exception e) {
            if (session.getTransaction().isActive()) {
                session.getTransaction().rollback();
            }
            logger.error("Failed to retrieve all Feedback", e);
        }
        return Collections.emptyList();
    }

    @Override
    public void deleteById(FeedbackDTO feedbackDTO) {
        Session session = feedbackDao.getCurrentSession();
        try {
            Transaction transaction = session.getTransaction();
            if (!transaction.isActive()) {
                session.beginTransaction();
            }
            feedbackDao.deleteById(feedbackDTO.getId());
            transaction.commit();
        } catch (Exception e) {
            if (session.getTransaction().isActive()) {
                session.getTransaction().rollback();
            }
            logger.error("Failed to delete Feedback by id", e);
        }
    }
}
