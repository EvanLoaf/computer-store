package com.gmail.evanloafakahaitao.computer.store.services.impl;

import com.gmail.evanloafakahaitao.computer.store.dao.UserDao;
import com.gmail.evanloafakahaitao.computer.store.dao.connection.ConnectionService;
import com.gmail.evanloafakahaitao.computer.store.dao.impl.UserDaoImpl;
import com.gmail.evanloafakahaitao.computer.store.dao.model.User;
import com.gmail.evanloafakahaitao.computer.store.services.UserService;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class UserServiceImpl implements UserService {

    private UserDao userDao = new UserDaoImpl();

    @Override
    public int update(User newUser) {
        int updatedRows = 0;
        Connection connection = ConnectionService.getInstance().getConnection();
        System.out.println("Updating user");
        User oldUser = userDao.findById(connection, newUser.getId());
        try {
            connection.setAutoCommit(false);
            updatedRows = userDao.update(connection, newUser, oldUser);
            connection.commit();
            System.out.printf("Updated %d rows in User%n", updatedRows);
        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException exc) {
                System.out.println("Error rolling back user update transaction");
                exc.printStackTrace();
            }
            e.printStackTrace();
        } finally {
            try {
                connection.setAutoCommit(true);
            } catch (SQLException e) {
                System.out.println("Error setting connection auto commit to true");
                e.printStackTrace();
            }
        }
        return updatedRows;
    }

    @Override
    public List<User> findAll() {
        List<User> users;
        Connection connection = ConnectionService.getInstance().getConnection();
        System.out.println("Retrieving all users");
        users = userDao.findAll(connection);
        System.out.printf("Retrieved users : %d%n", users.size());
        return users;
    }

    @Override
    public User findByEmail(String email) {
        User user;
        Connection connection = ConnectionService.getInstance().getConnection();
        System.out.println("Retrieving user by email");
        user = userDao.findByEmail(connection, email);
        System.out.printf("User with email %s is found : %s%n", email, user != null);
        return user;
    }
}
