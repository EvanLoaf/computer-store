package com.gmail.evanloafakahaitao.computer.store.controllers;

import com.gmail.evanloafakahaitao.computer.store.controllers.properties.PageProperties;
import com.gmail.evanloafakahaitao.computer.store.controllers.validators.UserValidator;
import com.gmail.evanloafakahaitao.computer.store.services.UserService;
import com.gmail.evanloafakahaitao.computer.store.services.dto.UserDTO;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/users")
public class UsersController {

    private static final Logger logger = LogManager.getLogger(UsersController.class);

    private final PageProperties pageProperties;
    private final UserValidator userValidator;
    private final UserService userService;

    @Autowired
    public UsersController(
            PageProperties pageProperties,
            UserValidator userValidator,
            UserService userService
    ) {
        this.pageProperties = pageProperties;
        this.userValidator = userValidator;
        this.userService = userService;
    }

    @GetMapping
    public String getUsers(
            ModelMap modelMap
    ) {
        List<UserDTO> users = userService.findAll();
        modelMap.addAttribute("users", users);
        return pageProperties.getUsersPagePath();
    }
}
