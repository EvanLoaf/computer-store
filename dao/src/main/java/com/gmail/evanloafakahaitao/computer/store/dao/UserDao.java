package com.gmail.evanloafakahaitao.computer.store.dao;

import com.gmail.evanloafakahaitao.computer.store.dao.model.User;

public interface UserDao extends GenericDao<User> {

    User findByEmail(String email);
}
