package com.gmail.evanloafakahaitao.computer.store.servlets.command.impl;

import com.gmail.evanloafakahaitao.computer.store.config.ConfigurationManager;
import com.gmail.evanloafakahaitao.computer.store.config.properties.PageProperties;
import com.gmail.evanloafakahaitao.computer.store.dao.model.User;
import com.gmail.evanloafakahaitao.computer.store.services.UserService;
import com.gmail.evanloafakahaitao.computer.store.services.impl.UserServiceImpl;
import com.gmail.evanloafakahaitao.computer.store.servlets.command.Command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class UsersCommand implements Command {

    private UserService userService = new UserServiceImpl();
    private ConfigurationManager configurationManager = ConfigurationManager.getInstance();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
        List<User> users = userService.findAll();
        if (users != null) {
            request.setAttribute("users", users);
        } else {
            request.setAttribute("error", "Error retrieving users");
        }
        return configurationManager.getProperty(PageProperties.USERS_PAGE_PATH);
    }
}
