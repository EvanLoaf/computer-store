package com.gmail.evanloafakahaitao.computer.store.controllers.validators;

import com.gmail.evanloafakahaitao.computer.store.services.UserService;
import com.gmail.evanloafakahaitao.computer.store.services.dto.SimpleUserDTO;
import com.gmail.evanloafakahaitao.computer.store.services.dto.UserDTO;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import java.util.Optional;
import java.util.regex.Pattern;

@Component("userValidator")
public class UserValidator implements Validator {
    
    private static final Logger logger = LogManager.getLogger(UserValidator.class);
    
    private final UserService userService;
    
    @Autowired
    public UserValidator(UserService userService) {
        this.userService = userService;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return UserDTO.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        UserDTO user = (UserDTO) target;
        if (user.getId() == null) {
            logger.info("Validating User - create");
            logger.debug("Validating User - create : {}", user);
            ValidationUtils.rejectIfEmpty(errors, "email", "user.email.empty");
            ValidationUtils.rejectIfEmpty(errors, "password", "user.password.empty");
            ValidationUtils.rejectIfEmpty(errors, "firstName", "user.firstname.empty");
            ValidationUtils.rejectIfEmpty(errors, "lastName", "user.lastname.empty");
            ValidationUtils.rejectIfEmpty(errors, "profile.address", "user.address.empty");
            ValidationUtils.rejectIfEmpty(errors, "profile.phoneNumber", "user.phonenumber.empty");

            Pattern emailPattern = Pattern.compile(
                    "^[\\w\\d.]+@[a-zA-Z\\d]+\\.[a-zA-Z]{2,6}$",
                    Pattern.CASE_INSENSITIVE
            );
            if (!(emailPattern.matcher(user.getEmail()).matches())) {
                errors.rejectValue("email", "user.email.invalid");
            }
            if (user.getEmail().length() > 30) {
                errors.rejectValue("email", "user.email.length");
            }
            if (user.getPassword() != null) {
                Pattern passwordPattern = Pattern.compile(
                        "^[\\w\\d.]{8,30}$",
                        Pattern.CASE_INSENSITIVE
                );
                if (!(passwordPattern.matcher(user.getPassword()).matches())) {
                    errors.rejectValue("password", "user.password.invalid");
                }
            }
            if (user.getLastName().length() > 25) {
                errors.rejectValue("lastName", "user.lastname.length");
            }
            if (user.getFirstName().length() > 25) {
                errors.rejectValue("firstName", "user.firstname.length");
            }
            if (user.getEmail() != null && user.getEmail().length() <= 30) {
                SimpleUserDTO simpleUserDTO = new SimpleUserDTO();
                simpleUserDTO.setEmail(user.getEmail());
                Optional<SimpleUserDTO> userByEmail = userService.findByEmail(simpleUserDTO);
                if (userByEmail.isPresent()) {
                    errors.rejectValue("email", "user.email.exists");
                }
            }
        } else {
            logger.info("Validating User - update");
            logger.debug("Validating User - update : {}", user);
            if (user.getPassword() != null && !user.getPassword().equals("")) {
                Pattern passwordPattern = Pattern.compile(
                        "^[\\w\\d.]{8,30}$",
                        Pattern.CASE_INSENSITIVE
                );
                if (!(passwordPattern.matcher(user.getPassword()).matches())) {
                    errors.rejectValue("password", "user.password.invalid");
                }
            }
            if (user.getLastName() != null) {
                if (user.getLastName().equals("")) {
                    errors.rejectValue("lastName", "user.lastname.empty");
                } else if (user.getLastName().length() > 25) {
                    errors.rejectValue("lastName", "user.lastname.length");
                }

            }
            if (user.getFirstName() != null) {
                if (user.getFirstName().equals("")) {
                    errors.rejectValue("firstName", "user.firstname.empty");
                } else if (user.getFirstName().length() > 25) {
                    errors.rejectValue("firstName", "user.firstname.length");
                }
            }
            if (user.getProfile().getAddress() != null) {
                if (user.getProfile().getAddress().equals("")) {
                    errors.rejectValue("profile.address", "user.address.empty");
                } else if (user.getProfile().getAddress().length() > 100) {
                    errors.rejectValue("profile.address", "user.address.length");
                }
            }
            if (user.getProfile().getPhoneNumber() != null) {
                if (user.getProfile().getPhoneNumber().equals("")) {
                    errors.rejectValue("profile.phoneNumber", "user.phonenumber.empty");
                } else if (user.getProfile().getPhoneNumber().length() > 15) {
                    errors.rejectValue("profile.phoneNumber", "user.phonenumber.length");
                }
            }
        }
    }
}
