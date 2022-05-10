package com.gmail.evanloafakahaitao.computer.store.controllers.validators;

import com.gmail.evanloafakahaitao.computer.store.services.model.DiscountDetails;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import java.util.regex.Pattern;

@Component("discountDetailsValidator")
public class DiscountDetailsValidator implements Validator {
    
    private static final Logger logger = LogManager.getLogger(DiscountDetailsValidator.class);
    
    @Override
    public boolean supports(Class<?> clazz) {
        return DiscountDetails.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        DiscountDetails discountDetails = (DiscountDetails) target;
        logger.info("Validating Discount Details");
        logger.debug("Validating Discount Details : {}", discountDetails);
        ValidationUtils.rejectIfEmpty(errors, "discountId", "discount.details.id.empty");
        ValidationUtils.rejectIfEmpty(errors, "minPrice", "discount.details.min.price.empty");
        ValidationUtils.rejectIfEmpty(errors, "maxPrice", "discount.details.max.price.empty");

        Pattern pattern = Pattern.compile(
                "^\\d{3,5}\\.?\\d*$",
                Pattern.CASE_INSENSITIVE
        );
        if (!(pattern.matcher(discountDetails.getMinPrice().toString()).matches())) {
            errors.rejectValue("minPrice", "discount.details.price.invalid");
        }
        if (!(pattern.matcher(discountDetails.getMaxPrice().toString()).matches())) {
            errors.rejectValue("minPrice", "discount.details.price.invalid");
        }
        if (discountDetails.getMaxPrice().compareTo(discountDetails.getMinPrice()) < 0) {
            errors.rejectValue("maxPrice", "discount.details.max.price.low");
        }
    }
}
