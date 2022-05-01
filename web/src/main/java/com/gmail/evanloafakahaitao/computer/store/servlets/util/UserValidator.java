package com.gmail.evanloafakahaitao.computer.store.servlets.util;

import com.gmail.evanloafakahaitao.computer.store.dao.model.User;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UserValidator {

    public boolean validate(User user) {
        String invalidMessage = "One or more of User fields are invalid";
        if (user.getEmail() != null) {
            Pattern emailPattern = Pattern.compile("^(?=[\\da-zA-Z@\\.\\_]{5,30}$)\\S+@\\S+\\.\\S+");
            Matcher emailMatcher = emailPattern.matcher(user.getEmail());
            if (!emailMatcher.find()) {
                System.out.println(invalidMessage);
                return false;
            }
        }
        if (user.getPassword() != null && (user.getPassword().length() > 30 || user.getPassword().length() < 4)) {
            System.out.println(invalidMessage);
            return false;
        }
        if (user.getFirstName() != null) {
            Pattern namePattern = Pattern.compile("^[a-zA-Z]{1,25}$");
            Matcher fnMatcher = namePattern.matcher(user.getFirstName());
            if (!fnMatcher.find()) {
                System.out.println(invalidMessage);
                return false;
            }
        }
        if (user.getLastName() != null) {
            Pattern namePattern = Pattern.compile("^[a-zA-Z]{1,25}$");
            Matcher lnMatcher = namePattern.matcher(user.getLastName());
            if (!lnMatcher.find()) {
                System.out.println(invalidMessage);
                return false;
            }
        }
        if (user.getPhoneNumber() != null) {
            Pattern phonePattern = Pattern.compile("^[\\+\\d][\\-\\da-zA-Z]{2,14}$");
            Matcher phoneMatcher = phonePattern.matcher(user.getPhoneNumber());
            if (!phoneMatcher.find()) {
                System.out.println(invalidMessage);
                return false;
            }
        }
        if (user.getAdditionalInfo() != null && user.getAdditionalInfo().length() > 100) {
            System.out.println(invalidMessage);
            return false;
        }
        System.out.println("User fields validated");
        return true;
    }
}
