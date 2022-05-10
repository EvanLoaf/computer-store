package com.gmail.evanloafakahaitao.computer.store.controllers.validators;

import com.gmail.evanloafakahaitao.computer.store.services.dto.NewOrderDTO;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import java.util.regex.Pattern;

@Component("orderValidator")
public class OrderValidator implements Validator {

    private static final Logger logger = LogManager.getLogger(OrderValidator.class);

    @Override
    public boolean supports(Class<?> clazz) {
        return NewOrderDTO.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        NewOrderDTO order = (NewOrderDTO) target;
        ValidationUtils.rejectIfEmpty(errors, "quantity", "order.quantity.empty");
        if (order.getQuantity() != null) {
            logger.info("Validating order - create");
            logger.debug("Validating order - create : {}", order);

            Pattern quantityPattern = Pattern.compile(
                    "^\\d{1,9}$"
            );
            if (!(quantityPattern.matcher(order.getQuantity().toString()).matches())) {
                errors.rejectValue("quantity", "order.quantity.invalid");
            }
            if (order.getQuantity() > 10) {
                errors.rejectValue("quantity", "order.quantity.bulk");
            }
        }
    }
}
