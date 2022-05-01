package com.gmail.evanloafakahaitao.computer.store.dao.impl;

import com.gmail.evanloafakahaitao.computer.store.dao.UserDao;
import com.gmail.evanloafakahaitao.computer.store.dao.model.User;
import com.gmail.evanloafakahaitao.computer.store.dao.util.UserConverter;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDaoImpl implements UserDao {

    private UserConverter userConverter = new UserConverter();

    @Override
    public List<User> findAll(Connection connection) {
        List<User> users = new ArrayList<>();
        String sql =
                "SELECT \n" +
                "   u.id, \n" +
                "   u.first_name, \n" +
                "   u.last_name, \n" +
                "   u.email, \n" +
                "   u.password, \n" +
                "   u.additional_info, \n" +
                "   u.phone_number, \n" +
                "   r.name AS role\n" +
                "FROM \n" +
                "   user AS u \n" +
                "JOIN \n" +
                "   role AS r \n" +
                "ON \n" +
                "   u.role_id = r.id;\n";
        try (
                PreparedStatement preparedStatement = connection.prepareStatement(sql);
                ResultSet resultSet = preparedStatement.executeQuery()
        ) {
            while (resultSet.next()) {
                User user = userConverter.toUser(resultSet);
                users.add(user);
            }
        } catch (SQLException e) {
            System.out.println("Error retrieving all users from DB");
            e.printStackTrace();
        }
        return users;
    }

    @Override
    public User findByEmail(Connection connection, String email) {
        User user = null;
        String sql =
                "SELECT \n" +
                "   u.id, \n" +
                "   u.first_name, \n" +
                "   u.last_name, \n" +
                "   u.email, \n" +
                "   u.password, \n" +
                "   u.additional_info, \n" +
                "   u.phone_number, \n" +
                "   r.name AS role\n" +
                "FROM \n" +
                "   user AS u \n" +
                "JOIN \n" +
                "   role AS r \n" +
                "ON \n" +
                "   u.role_id = r.id \n" +
                "WHERE \n" +
                "   u.email = ?;\n";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, email);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    user = userConverter.toUser(resultSet);
                }
            }
        } catch (SQLException e) {
            System.out.println("Error retrieving user by email from DB");
            e.printStackTrace();
        }
        return user;
    }

    @Override
    public int update(Connection connection, User newUser, User oldUser) throws SQLException {
        int updatedRows;
        String sql =
                "UPDATE \n" +
                "   user \n" +
                "SET \n" +
                "   first_name = ?, \n" +
                "   last_name = ?, \n" +
                "   email = ?, \n" +
                "   additional_info = ?, \n" +
                "   phone_number = ? \n" +
                "WHERE \n" +
                "   user.id = ?;\n";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(
                    1,
                    (newUser.getFirstName() == null) ? oldUser.getFirstName() : newUser.getFirstName()
            );
            preparedStatement.setString(
                    2,
                    (newUser.getLastName() == null) ? oldUser.getLastName() : newUser.getLastName()
            );
            preparedStatement.setString(
                    3,
                    (newUser.getEmail() == null) ? oldUser.getEmail() : newUser.getEmail()
            );
            preparedStatement.setString(
                    4,
                    (newUser.getAdditionalInfo() == null) ? oldUser.getAdditionalInfo() : newUser.getAdditionalInfo()
            );
            preparedStatement.setString(
                    5,
                    (newUser.getPhoneNumber() == null) ? oldUser.getPhoneNumber() : newUser.getPhoneNumber()
            );
            preparedStatement.setLong(6, newUser.getId());
            updatedRows = preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error updating User");
            e.printStackTrace();
            throw new SQLException(e);
        }
        return updatedRows;
    }

    @Override
    public User findById(Connection connection, Long id) {
        User user = null;
        String sql =
                "SELECT \n" +
                "   u.id, \n" +
                "   u.first_name, \n" +
                "   u.last_name, \n" +
                "   u.email, \n" +
                "   u.password, \n" +
                "   u.additional_info, \n" +
                "   u.phone_number, \n" +
                "   r.name AS role\n" +
                "FROM \n" +
                "   user AS u \n" +
                "JOIN \n" +
                "   role AS r \n" +
                "ON \n" +
                "   u.role_id = r.id \n" +
                "WHERE \n" +
                "   u.id = ?;\n";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setLong(1, id);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    user = userConverter.toUser(resultSet);
                }
            }
        } catch (SQLException e) {
            System.out.println("Error retrieving user by id from DB");
            e.printStackTrace();
        }
        return user;
    }
}
