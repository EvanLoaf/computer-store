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
import com.gmail.evanloafakahaitao.computer.store.services.dto.DiscountDTO;
import com.gmail.evanloafakahaitao.computer.store.services.dto.RoleDTO;
import com.gmail.evanloafakahaitao.computer.store.services.dto.SimpleUserDTO;
import com.gmail.evanloafakahaitao.computer.store.services.dto.UserDTO;
import com.gmail.evanloafakahaitao.computer.store.services.model.DiscountDetails;
import com.gmail.evanloafakahaitao.computer.store.services.util.CurrentUserUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
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
        List<UserDTO> users = userService.findAllNotDeleted(
                paginationUtil.getStartPosition(page),
                pageProperties.getPaginationMaxResults()
        );
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

    @GetMapping(value = "/profile")
    @PreAuthorize("hasAnyAuthority('view_user_self', 'update_user_self')")
    public String getUserProfile(
            ModelMap modelMap
    ) {
        logger.debug("Executing Users Controller method GET : getUserProfile");
        logger.info("Viewing User Profile");
        SimpleUserDTO userDTO = new SimpleUserDTO();
        userDTO.setId(CurrentUserUtil.getCurrentId());
        UserDTO user = userService.findById(userDTO);
        modelMap.addAttribute("user", user);
        return pageProperties.getUserProfilePagePath();
    }

    @PostMapping(value = "/{id}")
    @PreAuthorize("hasAuthority('update_user_self')")
    public String updateUser(
            @PathVariable("id") Long id,
            @ModelAttribute("user") UserDTO user,
            BindingResult result,
            ModelMap modelMap
    ) {
        logger.debug("Executing Users Controller method POST : updateUser with id : {}", id);
        logger.info("User updating their information");
        user = fieldTrimmer.trim(user);
        user.setId(id);
        userValidator.validate(user, result);
        if (result.hasErrors()) {
            modelMap.addAttribute("user", user);
            return pageProperties.getUserProfilePagePath();
        } else {
            userService.update(user);
            return "redirect:" + WebProperties.PUBLIC_ENTRY_POINT_PREFIX + "/users/profile" + "?update=true";
        }
    }

    @PostMapping(value = "/{id}/admin")
    @PreAuthorize("hasAuthority('update_users_all')")
    public String updateUserByAdmin(
            @PathVariable("id") Long id,
            @ModelAttribute("user") UserDTO user,
            BindingResult result,
            ModelMap modelMap
    ) {
        logger.debug("Executing Users Controller method POST : updateUserByAdmin with id : {}", id);
        logger.info("User information updated by administrator");
        user = fieldTrimmer.trim(user);
        user.setId(id);
        userValidator.validate(user, result);
        if (result.hasErrors()) {
            modelMap.addAttribute("user", user);
            return pageProperties.getUserUpdatePagePath();
        } else {
            userService.update(user);
            return "redirect:" + WebProperties.PUBLIC_ENTRY_POINT_PREFIX + "/users" + "?update=true";
        }
    }

    @GetMapping(value = "/{id}/update")
    @PreAuthorize("hasAuthority('update_users_all')")
    public String updateUserPage(
            @PathVariable("id") Long id,
            ModelMap modelMap
    ) {
        logger.debug("Executing Users Controller method GET : updateUserPage with id : {}", id);
        logger.info("Entering User update page");
        SimpleUserDTO userDTO = new SimpleUserDTO();
        userDTO.setId(id);
        UserDTO user = userService.findById(userDTO);
        List<RoleDTO> roles = roleService.findAll();
        modelMap.addAttribute("roles", roles);
        modelMap.addAttribute("user", user);
        return pageProperties.getUserUpdatePagePath();
    }

    @PostMapping(value = "/delete")
    @PreAuthorize("hasAuthority('delete_user')")
    public String deleteUsers(
            @RequestParam("ids") Long[] ids
    ) {
        logger.debug("Executing Users Controller method POST : deleteUsers with id's : {}", Arrays.toString(ids));
        logger.info("Deleting Users");
        for (Long id : ids) {
            SimpleUserDTO userDTO = new SimpleUserDTO();
            userDTO.setId(id);
            userService.deleteById(userDTO);
        }
        return "redirect:" + WebProperties.PUBLIC_ENTRY_POINT_PREFIX + "/users";
    }

    @GetMapping(value = "/{id}/disable")
    @PreAuthorize("hasAuthority('disable_user')")
    public String disableUser(
            @PathVariable("id") Long id,
            @RequestParam(value = "disable", defaultValue = "true") boolean disable
    ) {
        logger.debug("Executing Users Controller method GET : disableUser with id : {}", id);
        logger.info("Disabling User");
        UserDTO user = new UserDTO();
        user.setId(id);
        user.setDisabled(disable);
        userService.update(user);
        return "redirect:" + WebProperties.PUBLIC_ENTRY_POINT_PREFIX + "/users";
    }

    @PostMapping(value = "/discounts/update")
    @PreAuthorize("hasAuthority('update_discount_users')")
    public String updateUsersDiscount(
            @RequestParam("discountId") Long discountId
    ) {
        logger.debug("Executing Users Controller method POST : updateUsersDiscount with discount id : {}", discountId);
        logger.info("Updating Users Discount");
        DiscountDetails discountDetails = new DiscountDetails();
        discountDetails.setDiscountId(discountId);
        userService.updateDiscountAll(discountDetails);
        return "redirect:" + WebProperties.PUBLIC_ENTRY_POINT_PREFIX + "/items" + "?userdiscounts=true";
    }

    @GetMapping(value = "/discounts/update")
    @PreAuthorize("hasAuthority('update_discount_users')")
    public String updateUsersDiscountPage(
            ModelMap modelMap
    ) {
        logger.debug("Executing Users Controller method GET : updateUsersDiscountPage");
        logger.info("Entering Users Discount update page");
        List<DiscountDTO> discounts = discountService.findAll();
        modelMap.addAttribute("discounts", discounts);
        return pageProperties.getUsersSetDiscountPagePath();
    }
}
