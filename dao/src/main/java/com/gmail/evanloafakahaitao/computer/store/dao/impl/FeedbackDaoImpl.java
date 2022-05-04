package com.gmail.evanloafakahaitao.computer.store.dao.impl;

import com.gmail.evanloafakahaitao.computer.store.dao.FeedbackDao;
import com.gmail.evanloafakahaitao.computer.store.dao.model.Feedback;

public class FeedbackDaoImpl extends GenericDaoImpl<Feedback> implements FeedbackDao {

    public FeedbackDaoImpl() {
        super(Feedback.class);
    }
}
