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
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ApplicationController {

    private static final Logger logger = LogManager.getLogger(ApplicationController.class);

    private final PageProperties pageProperties;
    private final UserValidator userValidator;
    private final UserService userService;

    @Autowired
    public ApplicationController(
            PageProperties pageProperties,
            UserValidator userValidator,
            UserService userService
    ) {
        this.pageProperties = pageProperties;
        this.userValidator = userValidator;
        this.userService = userService;
    }

    @GetMapping("/register")
    public String registerPage(
            ModelMap modelMap
    ) {
        logger.debug("Executing Application Controller method : registerPage");
        logger.info("Entered register page");
        modelMap.addAttribute("user", new UserDTO());
        return pageProperties.getRegisterPagePath();
    }

    @PostMapping
    public String registerUser(
            @ModelAttribute("user") UserDTO user,
            BindingResult result,
            ModelMap modelMap
    ) {
        logger.debug("Executing Application Controller method : registerUser");
        userValidator.validate(user, result);
        if (result.hasErrors()) {
            modelMap.addAttribute("user", user);
            return pageProperties.getRegisterPagePath();
        } else {
            userService.save(user);
            return pageProperties.getLoginPagePath();
        }
    }
}
