package com.gmail.evanloafakahaitao.computer.store.dao;

import com.gmail.evanloafakahaitao.computer.store.dao.model.User;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public interface UserDao {

    List<User> findAll(Connection connection);

    User findByEmail(Connection connection, String email);

    int update(Connection connection, User newUser, User oldUser) throws SQLException;

    User findById(Connection connection, Long id);
}
