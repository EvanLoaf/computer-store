package com.gmail.evanloafakahaitao.computer.store.controllers;

import com.gmail.evanloafakahaitao.computer.store.controllers.properties.PageProperties;
import com.gmail.evanloafakahaitao.computer.store.controllers.properties.WebProperties;
import com.gmail.evanloafakahaitao.computer.store.controllers.util.FieldTrimmer;
import com.gmail.evanloafakahaitao.computer.store.services.BusinessCardService;
import com.gmail.evanloafakahaitao.computer.store.services.UserService;
import com.gmail.evanloafakahaitao.computer.store.services.dto.BusinessCardDTO;
import com.gmail.evanloafakahaitao.computer.store.services.dto.SimpleUserDTO;
import com.gmail.evanloafakahaitao.computer.store.services.dto.UserDTO;
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

@Controller
@RequestMapping(WebProperties.PUBLIC_ENTRY_POINT_PREFIX + "/cards")
public class BusinessCardController {

    private static final Logger logger = LogManager.getLogger(BusinessCardController.class);

    private final BusinessCardService businessCardService;
    private final UserService userService;
    private final Validator businessCardValidator;
    private final PageProperties pageProperties;
    private final FieldTrimmer fieldTrimmer;

    @Autowired
    public BusinessCardController(
            BusinessCardService businessCardService,
            UserService userService,
            @Qualifier("businessCardValidator") Validator businessCardValidator,
            PageProperties pageProperties,
            FieldTrimmer fieldTrimmer
    ) {
        this.businessCardService = businessCardService;
        this.userService = userService;
        this.businessCardValidator = businessCardValidator;
        this.pageProperties = pageProperties;
        this.fieldTrimmer = fieldTrimmer;
    }

    @GetMapping
    @PreAuthorize("hasAuthority('manage_business_card')")
    public String getBusinessCards(
            ModelMap modelMap
    ) {
        logger.debug("Executing BusinessCard Controller method GET : getBusinessCards");
        logger.info("Viewing Business Cards from User");
        SimpleUserDTO userDTO = new SimpleUserDTO();
        userDTO.setId(CurrentUserUtil.getCurrentId());
        UserDTO user = userService.findById(userDTO);
        modelMap.addAttribute("businessCards", user.getBusinessCards());
        return pageProperties.getBusinessCardsPagePath();
    }

    @PostMapping
    @PreAuthorize("hasAuthority('manage_business_card')")
    public String createBusinessCard(
            @ModelAttribute("businessCard") BusinessCardDTO businessCard,
            BindingResult result,
            ModelMap modelMap
    ) {
        logger.debug("Executing BusinessCard Controller method POST : createBusinessCard");
        logger.info("Creating Business Card");
        businessCard = fieldTrimmer.trim(businessCard);
        businessCardValidator.validate(businessCard, result);
        if (result.hasErrors()) {
            modelMap.addAttribute("businessCard", businessCard);
            return pageProperties.getBusinessCardCreatePagePath();
        } else {
            businessCardService.save(businessCard);
            return "redirect:" + WebProperties.PUBLIC_ENTRY_POINT_PREFIX + "/cards";
        }
    }

    @GetMapping(value = "/create")
    @PreAuthorize("hasAuthority('manage_business_card')")
    public String createBusinessCardPage(
            ModelMap modelMap
    ) {
        logger.debug("Executing BusinessCard Controller method GET : createBusinessCardPage");
        logger.info("Entering Business Card creation page");
        modelMap.addAttribute("businessCard", new BusinessCardDTO());
        return pageProperties.getBusinessCardCreatePagePath();
    }

    @GetMapping(value = "/{id}/delete")
    @PreAuthorize("hasAuthority('manage_business_card')")
    public String deleteBusinessCard(
            @PathVariable("id") Long id
    ) {
        logger.debug("Executing BusinessCard Controller method GET : deleteBusinessCardPage with id : {}", id);
        logger.info("Deleting Business Card");
        BusinessCardDTO businessCardDTO = new BusinessCardDTO();
        businessCardDTO.setId(id);
        businessCardService.deleteById(businessCardDTO);
        return "redirect:" + WebProperties.PUBLIC_ENTRY_POINT_PREFIX + "/cards";
    }
}
