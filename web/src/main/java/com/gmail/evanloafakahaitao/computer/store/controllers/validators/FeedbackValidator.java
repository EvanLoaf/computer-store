package com.gmail.evanloafakahaitao.computer.store.controllers.validators;

import com.gmail.evanloafakahaitao.computer.store.services.dto.FeedbackDTO;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component("feedbackValidator")
public class FeedbackValidator implements Validator {

    private static final Logger logger = LogManager.getLogger(FeedbackValidator.class);

    @Override
    public boolean supports(Class<?> clazz) {
        return FeedbackDTO.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        FeedbackDTO feedback = (FeedbackDTO) target;
        logger.info("Validating feedback - create");
        logger.debug("Validating feedback - create : {}", feedback);
        ValidationUtils.rejectIfEmpty(errors, "message", "feedback.message.empty");
        if (feedback.getMessage() != null && !feedback.getMessage().equals("") && feedback.getMessage().length() > 200) {
            errors.rejectValue("message", "feedback.message.length");
        }
    }
}
