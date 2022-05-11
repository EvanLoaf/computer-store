package com.gmail.evanloafakahaitao.computer.store.controllers;

import com.gmail.evanloafakahaitao.computer.store.controllers.properties.PageProperties;
import com.gmail.evanloafakahaitao.computer.store.controllers.properties.WebProperties;
import com.gmail.evanloafakahaitao.computer.store.controllers.util.FieldTrimmer;
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
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(WebProperties.PUBLIC_ENTRY_POINT_PREFIX)
public class ApplicationController {

    private static final Logger logger = LogManager.getLogger(ApplicationController.class);

    private final PageProperties pageProperties;
    private final UserValidator userValidator;
    private final FieldTrimmer fieldTrimmer;
    private final UserService userService;

    @Autowired
    public ApplicationController(
            PageProperties pageProperties,
            UserValidator userValidator,
            FieldTrimmer fieldTrimmer,
            UserService userService
    ) {
        this.pageProperties = pageProperties;
        this.userValidator = userValidator;
        this.fieldTrimmer = fieldTrimmer;
        this.userService = userService;
    }

    @GetMapping(value = "/register")
    public String registerUserPage(
            ModelMap modelMap
    ) {
        logger.debug("Executing Application Controller method GET : registerUserPage");
        logger.info("Entering register page");
        modelMap.addAttribute("user", new UserDTO());
        return pageProperties.getRegisterPagePath();
    }

    @PostMapping(value = "/register")
    public String registerUser(
            @ModelAttribute("user") UserDTO user,
            BindingResult result,
            ModelMap modelMap
    ) {
        logger.debug("Executing Application Controller method POST : registerUser");
        logger.info("Registering User");
        user = fieldTrimmer.trim(user);
        userValidator.validate(user, result);
        if (result.hasErrors()) {
            modelMap.addAttribute("user", user);
            return pageProperties.getRegisterPagePath();
        } else {
            userService.save(user);
            return pageProperties.getLoginPagePath();
        }
    }

    @GetMapping(value = "/login")
    public String loginPage(
            ModelMap modelMap
    ) {
        logger.debug("Executing Application Controller method GET : loginPage");
        logger.info("Entering login page");
        modelMap.addAttribute("user", new UserDTO());
        return pageProperties.getLoginPagePath();
    }
}
