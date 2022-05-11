package com.gmail.evanloafakahaitao.computer.store.controllers.api;

import com.gmail.evanloafakahaitao.computer.store.controllers.model.APIResponse;
import com.gmail.evanloafakahaitao.computer.store.controllers.properties.WebProperties;
import com.gmail.evanloafakahaitao.computer.store.services.BusinessCardService;
import com.gmail.evanloafakahaitao.computer.store.services.UserService;
import com.gmail.evanloafakahaitao.computer.store.services.dto.BusinessCardDTO;
import com.gmail.evanloafakahaitao.computer.store.services.dto.SimpleUserDTO;
import com.gmail.evanloafakahaitao.computer.store.services.dto.UserDTO;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping(WebProperties.REST_API_ENTRY_POINT_PREFIX + "/cards")
public class BusinessCardAPIController {

    private static final Logger logger = LogManager.getLogger(BusinessCardAPIController.class);

    private final BusinessCardService businessCardService;
    private final UserService userService;

    @Autowired
    public BusinessCardAPIController(BusinessCardService businessCardService, UserService userService) {
        this.businessCardService = businessCardService;
        this.userService = userService;
    }

    @GetMapping(value = "/users/{id}")
    @PreAuthorize("hasAuthority('manage_business_card_api')")
    public Set<BusinessCardDTO> getBusinessCards(
            @PathVariable(name = "id") Long userId
    ) {
        logger.debug("Executing BusinessCard API Controller method GET : getBusinessCards for user : {}", userId);
        logger.info("API : Viewing Business Cards");
        SimpleUserDTO userDTO = new SimpleUserDTO();
        userDTO.setId(userId);
        UserDTO user = userService.findById(userDTO);
        return user.getBusinessCards();
    }

    @DeleteMapping(value = "/{id}")
    @PreAuthorize("hasAuthority('manage_business_card_api')")
    public ResponseEntity<APIResponse> deleteBusinessCard(
            @PathVariable("id") Long id
    ) {
        logger.debug("Executing BusinessCard API Controller method DELETE : deleteBusinessCards with id : {}", id);
        logger.info("Deleting Business Card");
        BusinessCardDTO businessCard = new BusinessCardDTO();
        businessCard.setId(id);
        businessCardService.deleteById(businessCard);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
