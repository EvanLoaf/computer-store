package com.gmail.evanloafakahaitao.computer.store.dao.impl;

import com.gmail.evanloafakahaitao.computer.store.dao.NewsDao;
import com.gmail.evanloafakahaitao.computer.store.dao.model.News;

public class NewsDaoImpl extends GenericDaoImpl<News> implements NewsDao {

    public NewsDaoImpl() {
        super(News.class);
    }
}
