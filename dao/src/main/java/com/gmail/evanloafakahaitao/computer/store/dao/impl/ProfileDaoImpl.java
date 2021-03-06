package com.gmail.evanloafakahaitao.computer.store.dao.impl;

import com.gmail.evanloafakahaitao.computer.store.dao.ProfileDao;
import com.gmail.evanloafakahaitao.computer.store.dao.model.Profile;
import org.springframework.stereotype.Repository;

@Repository
public class ProfileDaoImpl extends GenericDaoImpl<Profile> implements ProfileDao {

    public ProfileDaoImpl() {
        super(Profile.class);
    }
}
