package com.gmail.evanloafakahaitao.computer.store.dao;

import com.gmail.evanloafakahaitao.computer.store.dao.model.User;

import java.util.List;

public interface UserDao extends GenericDao<User> {

    User findByEmail(String email);

    List<User> findAllNotDeleted(Integer startPosition, Integer maxResults);
}
