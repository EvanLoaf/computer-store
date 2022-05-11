package com.gmail.evanloafakahaitao.computer.store.controllers.validators.api;

import com.gmail.evanloafakahaitao.computer.store.services.ItemService;
import com.gmail.evanloafakahaitao.computer.store.services.dto.ItemDTO;
import com.gmail.evanloafakahaitao.computer.store.services.dto.SimpleItemDTO;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.*;
import java.util.regex.Pattern;

@Component
public class ItemAPIValidator {

    private static final Logger logger = LogManager.getLogger(ItemAPIValidator.class);
    private static final ResourceBundle rb = ResourceBundle.getBundle("messages", Locale.ENGLISH);

    private final ItemService itemService;

    @Autowired
    public ItemAPIValidator(ItemService itemService) {
        this.itemService = itemService;
    }

    public Set<String> validate(ItemDTO item) {
        Set<String> errors = new HashSet<>();
        if (item.getId() == null) {
            logger.info("Validating item - create API");
            logger.debug("Validating item - create API : {}", item);
            if (item.getVendorCode() == null || item.getVendorCode().equals("")) {
                errors.add(rb.getString("item.vendorcode.empty"));
            } else if (item.getVendorCode().length() != 10) {
                errors.add(rb.getString("item.vendorcode.length"));
            } else {
                SimpleItemDTO simpleItemDTO = new SimpleItemDTO();
                simpleItemDTO.setVendorCode(item.getVendorCode());
                Optional<ItemDTO> existingItem = itemService.findByVendorCode(simpleItemDTO);
                if (existingItem.isPresent()) {
                    errors.add(rb.getString("item.vendorcode.exists"));
                }
            }
            if (item.getName() == null || item.getName().equals("")) {
                errors.add(rb.getString("item.name.empty"));
            } else if (item.getName().length() > 50) {
                errors.add(rb.getString("item.name.length"));
            }

            if (item.getDescription() == null || item.getDescription().equals("")) {
                errors.add(rb.getString("item.description.empty"));
            } else if (item.getDescription().length() > 100) {
                errors.add(rb.getString("item.description.length"));
            }
            if (item.getPrice() == null) {
                errors.add(rb.getString("item.price.empty"));
            } else {
                Pattern pattern = Pattern.compile(
                        "^\\d{3,5}\\.?\\d*$",
                        Pattern.CASE_INSENSITIVE
                );
                if (!(pattern.matcher(item.getPrice().toString()).matches())) {
                    errors.add(rb.getString("item.price.invalid"));
                } else if (item.getPrice().compareTo(BigDecimal.valueOf(199.99)) <= 0) {
                    errors.add(rb.getString("item.price.low"));
                }
            }
        } else {
            logger.info("Validating item - update API");
            logger.debug("Validating item - update API : {}", item);
            if (item.getVendorCode() != null && item.getVendorCode().equals("")) {
                errors.add(rb.getString("item.vendorcode.empty"));
            } else if (item.getVendorCode() != null && item.getVendorCode().length() != 10) {
                errors.add(rb.getString("item.vendorcode.length"));
            }
            if (item.getName() != null && item.getName().equals("")) {
                errors.add(rb.getString("item.name.empty"));
            } else if (item.getName() != null && item.getName().length() > 50) {
                errors.add(rb.getString("item.name.length"));
            }
            if (item.getDescription() != null && item.getDescription().equals("")) {
                errors.add(rb.getString("item.description.empty"));
            } else if (item.getDescription() != null && item.getDescription().length() > 100) {
                errors.add(rb.getString("item.description.length"));
            }
            if (item.getPrice() != null) {
                Pattern pattern = Pattern.compile(
                        "^\\d{3,5}\\.?\\d*$",
                        Pattern.CASE_INSENSITIVE
                );
                if (!(pattern.matcher(item.getPrice().toString()).matches())) {
                    errors.add(rb.getString("item.price.invalid"));
                } else if (item.getPrice().compareTo(BigDecimal.valueOf(199.99)) <= 0) {
                    errors.add(rb.getString("item.price.low"));
                }
            }
        }
        return errors;
    }
}
