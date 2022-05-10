package com.gmail.evanloafakahaitao.computer.store.controllers;

import com.gmail.evanloafakahaitao.computer.store.controllers.model.PaginationDetails;
import com.gmail.evanloafakahaitao.computer.store.controllers.properties.PageProperties;
import com.gmail.evanloafakahaitao.computer.store.controllers.properties.WebProperties;
import com.gmail.evanloafakahaitao.computer.store.controllers.util.FieldTrimmer;
import com.gmail.evanloafakahaitao.computer.store.controllers.util.PaginationUtil;
import com.gmail.evanloafakahaitao.computer.store.services.DiscountService;
import com.gmail.evanloafakahaitao.computer.store.services.ItemService;
import com.gmail.evanloafakahaitao.computer.store.services.dto.ItemDTO;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.Validator;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping(WebProperties.PUBLIC_ENTRY_POINT_PREFIX + "/items")
public class ItemsController {

    private static final Logger logger = LogManager.getLogger(ItemsController.class);

    private final PageProperties pageProperties;
    private final ItemService itemService;
    private final PaginationUtil paginationUtil;
    private final DiscountService discountService;
    private final Validator itemValidator;
    private final Validator itemDiscountDataValidator;
    private final FieldTrimmer fieldTrimmerUtil;

    public ItemsController(
            PageProperties pageProperties,
            ItemService itemService,
            PaginationUtil paginationUtil,
            DiscountService discountService,
            @Qualifier("itemValidator") Validator itemValidator,
            @Qualifier("discountDetailsValidator") Validator discountDetailsValidator,
            FieldTrimmer fieldTrimmerUtil
    ) {
        this.pageProperties = pageProperties;
        this.itemService = itemService;
        this.paginationUtil = paginationUtil;
        this.discountService = discountService;
        this.itemValidator = itemValidator;
        this.itemDiscountDataValidator = discountDetailsValidator;
        this.fieldTrimmerUtil = fieldTrimmerUtil;
    }

    @GetMapping
    @PreAuthorize("hasAuthority('view_items')")
    public String getItems(
            @RequestParam(value = "page", defaultValue = "1") Integer page,
            ModelMap modelMap
    ) {
        logger.debug("Executing Item Controller method GET : getItems, page : {}", page);
        logger.info("Viewing all Items");
        List<ItemDTO> items = itemService.findAllNotDeleted(paginationUtil.getStartPosition(page), pageProperties.getPaginationMaxResults());
        modelMap.addAttribute("items", items);
        PaginationDetails pagination = new PaginationDetails();
        pagination.setPage(page);
        pagination.setPageNumbers(
                paginationUtil.getPageNumbers(itemService.countAllNotDeleted().intValue())
        );
        pagination.setStartPosition(paginationUtil.getPageNumerationStart(page));
        modelMap.addAttribute("pagination", pagination);
        return pageProperties.getItemsPagePath();
    }
}
