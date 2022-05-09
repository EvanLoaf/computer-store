package com.gmail.evanloafakahaitao.computer.store.dao.impl;

import com.gmail.evanloafakahaitao.computer.store.dao.BusinessCardDao;
import com.gmail.evanloafakahaitao.computer.store.dao.model.BusinessCard;
import org.springframework.stereotype.Repository;

@Repository
public class BusinessCardDaoImpl extends GenericDaoImpl<BusinessCard> implements BusinessCardDao {

    public BusinessCardDaoImpl() {
        super(BusinessCard.class);
    }
}
