package com.gmail.evanloafakahaitao.computer.store.dao.util;

import com.gmail.evanloafakahaitao.computer.store.dao.model.User;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserConverter {

    public User toUser(ResultSet resultSet) {
        Long id = null;
        String firstName = null;
        String lastName = null;
        String email = null;
        String password = null;
        String additionalInfo = null;
        String phoneNumber = null;
        RoleEnum role = null;
        try {
            id = resultSet.getLong("id");
            firstName = resultSet.getString("first_name");
            lastName = resultSet.getString("last_name");
            email = resultSet.getString("email");
            password = resultSet.getString("password");
            additionalInfo = resultSet.getString("additional_info");
            phoneNumber = resultSet.getString("phone_number");
            role = RoleEnum.getRole(resultSet.getString("role"));
        } catch (SQLException e) {
            System.out.println("Error fetching User fields from ResultSet");
            e.printStackTrace();
        }
        return User.newBuilder()
                .withId(id)
                .withFirstName(firstName)
                .withLastName(lastName)
                .withEmail(email)
                .withPassword(password)
                .withAdditionalInfo(additionalInfo)
                .withPhoneNumber(phoneNumber)
                .withRole(role)
                .build();
    }
}
