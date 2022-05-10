package com.gmail.evanloafakahaitao.computer.store.controllers.util;

import com.gmail.evanloafakahaitao.computer.store.services.dto.UserDTO;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;

@Component
public class FieldTrimmer {

    private static final Logger logger = LogManager.getLogger(FieldTrimmer.class);

    public UserDTO trim(UserDTO user) {
        logger.info("Trimming User DTO");
        logger.debug("Trimming User DTO : {}", user);
        if (user.getFirstName() != null) {
            user.setFirstName(user.getFirstName().trim());
        }
        if (user.getLastName() != null) {
            user.setLastName(user.getLastName().trim());
        }
        if (user.getEmail() != null) {
            user.setEmail(user.getEmail().trim());
        }
        if (user.getPassword() != null) {
            user.setPassword(user.getPassword().trim());
        }
        if (user.getProfile().getAddress() != null) {
            user.getProfile().setAddress(user.getProfile().getAddress().trim());
        }
        if (user.getProfile().getPhoneNumber() != null) {
            user.getProfile().setPhoneNumber(user.getProfile().getPhoneNumber().trim());
        }
        return user;
    }


}
