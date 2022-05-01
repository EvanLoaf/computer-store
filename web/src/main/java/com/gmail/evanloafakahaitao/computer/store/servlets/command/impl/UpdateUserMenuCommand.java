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

public class UpdateUserMenuCommand implements Command {

    private UserService userService = new UserServiceImpl();
    private ConfigurationManager configurationManager = ConfigurationManager.getInstance();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String email = request.getParameter("email");
        User user = userService.findByEmail(email);
        if (user != null) {
            request.setAttribute("user", user);
            return configurationManager.getProperty(PageProperties.UPDATE_USER_MENU_PAGE_PATH);
        } {
            request.setAttribute("error", "Could not find selected user");
            return configurationManager.getProperty(PageProperties.USERS_PAGE_PATH);
        }
    }
}
