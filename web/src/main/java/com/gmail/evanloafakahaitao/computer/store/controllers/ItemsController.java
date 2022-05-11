package com.gmail.evanloafakahaitao.computer.store.controllers;

import com.gmail.evanloafakahaitao.computer.store.controllers.model.PaginationDetails;
import com.gmail.evanloafakahaitao.computer.store.controllers.properties.PageProperties;
import com.gmail.evanloafakahaitao.computer.store.controllers.properties.WebProperties;
import com.gmail.evanloafakahaitao.computer.store.controllers.util.FieldTrimmer;
import com.gmail.evanloafakahaitao.computer.store.controllers.util.PaginationUtil;
import com.gmail.evanloafakahaitao.computer.store.services.DiscountService;
import com.gmail.evanloafakahaitao.computer.store.services.ItemService;
import com.gmail.evanloafakahaitao.computer.store.services.dto.DiscountDTO;
import com.gmail.evanloafakahaitao.computer.store.services.dto.ItemDTO;
import com.gmail.evanloafakahaitao.computer.store.services.dto.SimpleItemDTO;
import com.gmail.evanloafakahaitao.computer.store.services.model.DiscountDetails;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Arrays;
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

    @Autowired
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
        List<ItemDTO> items = itemService.findAllNotDeleted(
                paginationUtil.getStartPosition(page),
                pageProperties.getPaginationMaxResults()
        );
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

    @PostMapping
    @PreAuthorize("hasAuthority('create_item')")
    public String createItem(
            @ModelAttribute("item") ItemDTO item,
            BindingResult result,
            ModelMap modelMap
    ) {
        logger.debug("Executing Item Controller method POST : createItem");
        logger.info("Creating Item");
        item = fieldTrimmerUtil.trim(item);
        itemValidator.validate(item, result);
        if (result.hasErrors()) {
            modelMap.addAttribute("item", item);
            return pageProperties.getItemCreatePagePath();
        } else {
            itemService.save(item);
            return "redirect:" + WebProperties.PUBLIC_ENTRY_POINT_PREFIX + "/items";
        }
    }

    @GetMapping(value = "/create")
    @PreAuthorize("hasAuthority('create_item')")
    public String createItemPage(
            ModelMap modelMap
    ) {
        logger.debug("Executing Item Controller method GET : createItemPage");
        logger.info("Entering Item creation page");
        modelMap.addAttribute("item", new ItemDTO());
        return pageProperties.getItemCreatePagePath();
    }

    @GetMapping(value = "/upload")
    @PreAuthorize("hasAuthority('upload_items')")
    public String uploadItemsPage() {
        logger.debug("Executing Item Controller method GET : uploadItemsPage");
        logger.info("Entering Item upload page");
        return pageProperties.getItemsUploadPagePath();
    }

    @PostMapping(value = "/upload")
    @PreAuthorize("hasAuthority('upload_items')")
    public String uploadItems(
            @RequestParam("file") MultipartFile multipartItems,
            ModelMap modelMap
    ) {
        logger.debug("Executing Item Controller method POST : uploadItems");
        logger.info("Uploading Items");
        List<String> vendorCodeDuplicates = itemService.save(multipartItems);
        if (!vendorCodeDuplicates.isEmpty()) {
            modelMap.addAttribute("duplicates", vendorCodeDuplicates);
            return pageProperties.getItemsUploadPagePath();
        } else {
            return "redirect:" + WebProperties.PUBLIC_ENTRY_POINT_PREFIX + "/items";
        }
    }

    @PostMapping(value = "/delete")
    @PreAuthorize("hasAuthority('delete_item')")
    public String deleteItems(
            @RequestParam("ids") Long[] ids
    ) {
        logger.debug("Executing Item Controller method POST : deleteItems with Ids : {} ", Arrays.toString(ids));
        logger.info("Deleting Items");
        for (Long id : ids) {
            SimpleItemDTO itemDTO = new SimpleItemDTO();
            itemDTO.setId(id);
            itemService.deleteById(itemDTO);
        }
        return "redirect:" + WebProperties.PUBLIC_ENTRY_POINT_PREFIX + "/items";
    }

    @GetMapping(value = "/discounts/update")
    @PreAuthorize("hasAuthority('update_discount_item')")
    public String updateItemsDiscountPage(
            ModelMap modelMap
    ) {
        logger.debug("Executing Item Controller method GET : updateItemsDiscountPage");
        logger.info("Entering Item Discount update Page");
        List<DiscountDTO> discounts = discountService.findAll();
        modelMap.addAttribute("discounts", discounts);
        modelMap.addAttribute("discountDetails", new DiscountDetails());
        return pageProperties.getItemsSetDiscountPagePath();
    }

    @PostMapping(value = "/discounts/update")
    @PreAuthorize("hasAuthority('update_discount_item')")
    public String updateItemsDiscount(
            @ModelAttribute("discountDetails") DiscountDetails discountDetails,
            BindingResult result,
            ModelMap modelMap
    ) {
        logger.debug("Executing Item Controller method POST : updateItemsDiscount with discount id : {}", discountDetails.getDiscountId());
        logger.info("Updating Items discount");
        itemDiscountDataValidator.validate(discountDetails, result);
        if (result.hasErrors()) {
            List<DiscountDTO> discounts = discountService.findAll();
            modelMap.addAttribute("discounts", discounts);
            modelMap.addAttribute("discountData", discountDetails);
            return pageProperties.getItemsSetDiscountPagePath();
        } else {
            itemService.updateDiscountInPriceRange(discountDetails);
            return "redirect:" + WebProperties.PUBLIC_ENTRY_POINT_PREFIX + "/items" + "?itemdiscounts=true";
        }

    }
}
