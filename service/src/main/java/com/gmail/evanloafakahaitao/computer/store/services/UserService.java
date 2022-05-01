package com.gmail.evanloafakahaitao.computer.store.services;

import com.gmail.evanloafakahaitao.computer.store.dao.model.User;

import java.util.List;

public interface UserService {

    List<User> findAll();

    User findByEmail(String email);

    int update(User newUser);
}
