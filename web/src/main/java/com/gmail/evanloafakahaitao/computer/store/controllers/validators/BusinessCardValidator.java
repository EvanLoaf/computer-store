package com.gmail.evanloafakahaitao.computer.store.controllers.validators;

import com.gmail.evanloafakahaitao.computer.store.services.dto.BusinessCardDTO;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component("businessCardValidator")
public class BusinessCardValidator implements Validator {

    private static final Logger logger = LogManager.getLogger(BusinessCardValidator.class);

    @Override
    public boolean supports(Class<?> clazz) {
        return BusinessCardDTO.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        BusinessCardDTO businessCard = (BusinessCardDTO) target;
        logger.info("Validating business card - create");
        logger.debug("Validating business card - create : {}", businessCard);
        ValidationUtils.rejectIfEmpty(errors, "title", "business.card.title.empty");
        ValidationUtils.rejectIfEmpty(errors, "name", "business.card.name.empty");
        ValidationUtils.rejectIfEmpty(errors, "phoneNumber", "business.card.phone.number.empty");
        if (!businessCard.getTitle().equals("") && businessCard.getTitle().length() > 20) {
            errors.rejectValue("title", "business.card.title.length");
        }
        if (!businessCard.getName().equals("") && businessCard.getName().length() > 40) {
            errors.rejectValue("name", "business.card.name.length");
        }
        if (!businessCard.getPhoneNumber().equals("") && businessCard.getPhoneNumber().length() > 20) {
            errors.rejectValue("phoneNumber", "business.card.phone.number.length");
        }
    }
}
