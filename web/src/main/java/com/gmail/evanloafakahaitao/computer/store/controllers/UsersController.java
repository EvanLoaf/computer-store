package com.gmail.evanloafakahaitao.computer.store.controllers;

import com.gmail.evanloafakahaitao.computer.store.controllers.model.PaginationDetails;
import com.gmail.evanloafakahaitao.computer.store.controllers.properties.PageProperties;
import com.gmail.evanloafakahaitao.computer.store.controllers.properties.WebProperties;
import com.gmail.evanloafakahaitao.computer.store.controllers.util.FieldTrimmer;
import com.gmail.evanloafakahaitao.computer.store.controllers.util.PaginationUtil;
import com.gmail.evanloafakahaitao.computer.store.controllers.validators.UserValidator;
import com.gmail.evanloafakahaitao.computer.store.services.DiscountService;
import com.gmail.evanloafakahaitao.computer.store.services.RoleService;
import com.gmail.evanloafakahaitao.computer.store.services.UserService;
import com.gmail.evanloafakahaitao.computer.store.services.dto.UserDTO;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.Validator;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping(WebProperties.PUBLIC_ENTRY_POINT_PREFIX + "/users")
public class UsersController {

    private static final Logger logger = LogManager.getLogger(UsersController.class);

    private final PageProperties pageProperties;
    private final UserService userService;
    private final RoleService roleService;
    private final DiscountService discountService;
    private final PaginationUtil paginationUtil;
    private final Validator userValidator;
    private final FieldTrimmer fieldTrimmer;

    @Autowired
    public UsersController(
            PageProperties pageProperties,
            @Qualifier("userValidator") UserValidator userValidator,
            UserService userService,
            RoleService roleService,
            DiscountService discountService,
            PaginationUtil paginationUtil,
            FieldTrimmer fieldTrimmer) {
        this.pageProperties = pageProperties;
        this.userValidator = userValidator;
        this.userService = userService;
        this.roleService = roleService;
        this.discountService = discountService;
        this.paginationUtil = paginationUtil;
        this.fieldTrimmer = fieldTrimmer;
    }

    @GetMapping
    @PreAuthorize("hasAnyAuthority('view_users_all')")
    public String getUsers(
            @RequestParam(value = "page", defaultValue = "1") Integer page,
            ModelMap modelMap
    ) {
        logger.debug("Executing Users Controller method GET : getUsers, page : {}", page);
        logger.info("Viewing all Users");
        List<UserDTO> users = userService.findAllNotDeleted(paginationUtil.getStartPosition(page), pageProperties.getPaginationMaxResults());
        modelMap.addAttribute("users", users);
        PaginationDetails pagination = new PaginationDetails();
        pagination.setPage(page);
        pagination.setPageNumbers(
                paginationUtil.getPageNumbers(userService.countAllNotDeleted().intValue())
        );
        pagination.setStartPosition(paginationUtil.getPageNumerationStart(page));
        modelMap.addAttribute("pagination", pagination);
        return pageProperties.getUsersPagePath();
    }
}
