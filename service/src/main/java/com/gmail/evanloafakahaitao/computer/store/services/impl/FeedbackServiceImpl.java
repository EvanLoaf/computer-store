package com.gmail.evanloafakahaitao.computer.store.services.impl;

import com.gmail.evanloafakahaitao.computer.store.dao.FeedbackDao;
import com.gmail.evanloafakahaitao.computer.store.dao.UserDao;
import com.gmail.evanloafakahaitao.computer.store.dao.impl.FeedbackDaoImpl;
import com.gmail.evanloafakahaitao.computer.store.dao.impl.UserDaoImpl;
import com.gmail.evanloafakahaitao.computer.store.dao.model.Feedback;
import com.gmail.evanloafakahaitao.computer.store.dao.model.User;
import com.gmail.evanloafakahaitao.computer.store.services.FeedbackService;
import com.gmail.evanloafakahaitao.computer.store.services.converter.DTOConverter;
import com.gmail.evanloafakahaitao.computer.store.services.converter.EntityConverter;
import com.gmail.evanloafakahaitao.computer.store.services.converter.dto.FeedbackDTOConverter;
import com.gmail.evanloafakahaitao.computer.store.services.converter.entity.FeedbackEntityConverter;
import com.gmail.evanloafakahaitao.computer.store.services.dto.FeedbackDTO;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.Collections;
import java.util.List;

public class FeedbackServiceImpl implements FeedbackService {

    private static final Logger logger = LogManager.getLogger(FeedbackServiceImpl.class);

    private final FeedbackDao feedbackDao = new FeedbackDaoImpl();
    private final UserDao userDao = new UserDaoImpl();
    private final EntityConverter<FeedbackDTO, Feedback> feedbackEntityConverter = new FeedbackEntityConverter();
    private final DTOConverter<FeedbackDTO, Feedback> feedbackDTOConverter = new FeedbackDTOConverter();

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
