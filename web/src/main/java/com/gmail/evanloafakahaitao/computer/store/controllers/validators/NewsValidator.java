package com.gmail.evanloafakahaitao.computer.store.controllers.validators;

import com.gmail.evanloafakahaitao.computer.store.services.dto.NewsDTO;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component("newsValidator")
public class NewsValidator implements Validator {

    private static final Logger logger = LogManager.getLogger(NewsValidator.class);

    @Override
    public boolean supports(Class<?> clazz) {
        return NewsDTO.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        NewsDTO news = (NewsDTO) target;
        if (news.getId() == null) {
            logger.info("Validating news - create");
            logger.debug("Validating news - create : {}", news);
            ValidationUtils.rejectIfEmpty(errors, "title", "news.title.empty");
            ValidationUtils.rejectIfEmpty(errors, "content", "news.content.empty");
            if (news.getTitle().length() > 40) {
                errors.rejectValue("title", "news.title.length");
            }
            if (news.getContent().length() > 500) {
                errors.rejectValue("content", "news.content.length");
            }
        } else {
            logger.info("Validating news - update");
            logger.debug("Validating news - update : {}", news);
            if (news.getTitle() != null && !news.getTitle().equals("") && news.getTitle().length() > 40) {
                errors.rejectValue("title", "news.title.length");
            }
            if (news.getContent() != null && !news.getContent().equals("") && news.getContent().length() > 500) {
                errors.rejectValue("content", "news.content.length");
            }
        }
    }
}
