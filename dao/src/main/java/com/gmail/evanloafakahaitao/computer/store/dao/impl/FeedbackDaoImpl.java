package com.gmail.evanloafakahaitao.computer.store.dao.impl;

import com.gmail.evanloafakahaitao.computer.store.dao.FeedbackDao;
import com.gmail.evanloafakahaitao.computer.store.dao.model.Feedback;
import org.springframework.stereotype.Repository;

@Repository
public class FeedbackDaoImpl extends GenericDaoImpl<Feedback> implements FeedbackDao {

    public FeedbackDaoImpl() {
        super(Feedback.class);
    }
}
