package com.gmail.evanloafakahaitao.computer.store.controllers.validators;

import com.gmail.evanloafakahaitao.computer.store.services.ItemService;
import com.gmail.evanloafakahaitao.computer.store.services.dto.ItemDTO;
import com.gmail.evanloafakahaitao.computer.store.services.dto.SimpleItemDTO;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import java.math.BigDecimal;
import java.util.Optional;
import java.util.regex.Pattern;

@Component("itemValidator")
public class ItemValidator implements Validator {
    
    private static final Logger logger = LogManager.getLogger(ItemValidator.class);
    
    private final ItemService itemService;
    
    @Autowired
    public ItemValidator(ItemService itemService) {
        this.itemService = itemService;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return ItemDTO.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        ItemDTO item = (ItemDTO) target;
        logger.info("Validating Item");
        logger.debug("Validating Item : {}", item);
        ValidationUtils.rejectIfEmpty(errors, "vendorCode", "item.vendorcode.empty");
        ValidationUtils.rejectIfEmpty(errors, "name", "item.name.empty");
        ValidationUtils.rejectIfEmpty(errors, "description", "item.description.empty");
        ValidationUtils.rejectIfEmpty(errors, "price", "item.price.empty");
        if (item.getVendorCode().length() != 10) {
            errors.rejectValue("vendorCode", "item.vendorcode.length");
        }
        if (item.getName().length() > 50) {
            errors.rejectValue("name", "item.name.length");
        }
        if (item.getDescription().length() > 100) {
            errors.rejectValue("description", "item.description.length");
        }
        if (item.getVendorCode() != null && item.getVendorCode().length() == 10) {
            SimpleItemDTO simpleItemDTO = new SimpleItemDTO();
            simpleItemDTO.setVendorCode(item.getVendorCode());
            Optional<ItemDTO> itemByVendorCode = itemService.findByVendorCode(simpleItemDTO);
            if (itemByVendorCode.isPresent()) {
                errors.rejectValue("vendorCode", "item.vendorcode.exists");
            }
        }
        if (item.getPrice() != null) {
            Pattern pattern = Pattern.compile(
                    "^\\d{3,5}\\.?\\d*$",
                    Pattern.CASE_INSENSITIVE
            );
            if (!(pattern.matcher(item.getPrice().toString()).matches())) {
                errors.rejectValue("price", "item.price.invalid");
            } else if (item.getPrice().compareTo(BigDecimal.valueOf(199.99)) >= 0) {
                errors.rejectValue("price", "item.price.low");
            }
        }

    }
}
