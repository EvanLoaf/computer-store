package com.gmail.evanloafakahaitao.computer.store.services.impl;

import com.gmail.evanloafakahaitao.computer.store.dao.FeedbackDao;
import com.gmail.evanloafakahaitao.computer.store.dao.UserDao;
import com.gmail.evanloafakahaitao.computer.store.dao.model.Feedback;
import com.gmail.evanloafakahaitao.computer.store.dao.model.User;
import com.gmail.evanloafakahaitao.computer.store.services.FeedbackService;
import com.gmail.evanloafakahaitao.computer.store.services.converters.DTOConverter;
import com.gmail.evanloafakahaitao.computer.store.services.converters.EntityConverter;
import com.gmail.evanloafakahaitao.computer.store.services.dto.FeedbackDTO;
import com.gmail.evanloafakahaitao.computer.store.services.util.CurrentUserUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;

@Service
@Transactional
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
        logger.info("Saving Feedback");
        logger.debug("Saving Feedback : {}", feedbackDTO);
        Feedback feedback = feedbackEntityConverter.toEntity(feedbackDTO);
        User user = userDao.findOne(
                CurrentUserUtil.getCurrentId()
        );
        feedback.setUser(user);
        feedbackDao.create(feedback);
        return feedbackDTOConverter.toDto(feedback);
    }

    @Override
    @Transactional(readOnly = true)
    public List<FeedbackDTO> findAll(Integer firstResult, Integer maxResults) {
        logger.info("Retrieving Feedback");
        List<Feedback> feedbackList = feedbackDao.findAll(firstResult, maxResults);
        logger.debug("Retrieved Feedback : {}", feedbackList);
        if (!feedbackList.isEmpty()) {
            return feedbackDTOConverter.toDTOList(feedbackList);
        } else return Collections.emptyList();
    }

    @Override
    public void deleteById(FeedbackDTO feedbackDTO) {
        logger.info("Deleting Feedback by Id");
        logger.debug("Deleting Feedback with Id : {}", feedbackDTO.getId());
        feedbackDao.deleteById(feedbackDTO.getId());
    }

    @Override
    public Long countAll() {
        logger.info("Counting all Feedback");
        return feedbackDao.countAll();
    }
}
