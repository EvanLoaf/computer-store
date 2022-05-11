package com.gmail.evanloafakahaitao.computer.store.controllers.validators;

import com.gmail.evanloafakahaitao.computer.store.services.dto.CommentDTO;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component("commentValidator")
public class CommentValidator implements Validator {

    private static final Logger logger = LogManager.getLogger(CommentValidator.class);

    @Override
    public boolean supports(Class<?> clazz) {
        return CommentDTO.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        CommentDTO comment = (CommentDTO) target;
        logger.info("Validating comment - create");
        logger.debug("Validating comment - create : {}", comment);
        ValidationUtils.rejectIfEmpty(errors, "content", "comment.content.empty");
        if (comment.getContent() != null && !comment.getContent().equals("") && comment.getContent().length() > 300) {
            errors.rejectValue("content", "comment.content.length");
        }
    }
}
