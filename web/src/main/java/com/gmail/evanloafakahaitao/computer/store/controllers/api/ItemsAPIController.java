package com.gmail.evanloafakahaitao.computer.store.controllers.api;

import com.gmail.evanloafakahaitao.computer.store.controllers.model.APIResponse;
import com.gmail.evanloafakahaitao.computer.store.controllers.properties.APIResponseProperties;
import com.gmail.evanloafakahaitao.computer.store.controllers.properties.PageProperties;
import com.gmail.evanloafakahaitao.computer.store.controllers.properties.WebProperties;
import com.gmail.evanloafakahaitao.computer.store.controllers.util.FieldTrimmer;
import com.gmail.evanloafakahaitao.computer.store.controllers.util.PaginationUtil;
import com.gmail.evanloafakahaitao.computer.store.controllers.validators.api.ItemAPIValidator;
import com.gmail.evanloafakahaitao.computer.store.services.ItemService;
import com.gmail.evanloafakahaitao.computer.store.services.dto.ItemDTO;
import com.gmail.evanloafakahaitao.computer.store.services.dto.SimpleItemDTO;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@RestController
@RequestMapping(WebProperties.REST_API_ENTRY_POINT_PREFIX + "/items")
public class ItemsAPIController {

    private static final Logger logger = LogManager.getLogger(ItemsAPIController.class);

    private final ItemService itemService;
    private final ItemAPIValidator validator;
    private final PageProperties pageProperties;
    private final PaginationUtil paginationUtil;
    private final FieldTrimmer fieldTrimmer;

    @Autowired
    public ItemsAPIController(
            ItemService itemService,
            ItemAPIValidator validator,
            PageProperties pageProperties,
            PaginationUtil paginationUtil,
            FieldTrimmer fieldTrimmer
    ) {
        this.itemService = itemService;
        this.validator = validator;
        this.pageProperties = pageProperties;
        this.paginationUtil = paginationUtil;
        this.fieldTrimmer = fieldTrimmer;
    }

    @GetMapping
    @PreAuthorize("hasAuthority('view_items_api')")
    public List<ItemDTO> getItems(
            @RequestParam(value = "page", defaultValue = "1") Integer page
    ) {
        logger.debug("Executing Item API Controller method GET : getItems");
        logger.info("API : viewing items");
        return itemService.findAllNotDeleted(
                paginationUtil.getStartPosition(page),
                pageProperties.getPaginationMaxResults()
        );
    }

    @PostMapping
    @PreAuthorize("hasAuthority('create_item_api')")
    public ResponseEntity<APIResponse> createItem(
            @RequestBody ItemDTO item
    ) {
        logger.debug("Executing Item API Controller method POST : createItem");
        logger.info("API : creating item");
        item = fieldTrimmer.trim(item);
        Set<String> errors = validator.validate(item);
        APIResponse response = new APIResponse();
        if (!errors.isEmpty()) {
            response.setMessage(APIResponseProperties.ITEM_NOT_CREATED);
            response.setErrors(errors);
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        } else {
            itemService.save(item);
            response.setMessage(APIResponseProperties.ITEM_CREATED);
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        }
    }

    @GetMapping(value = "/{id}")
    @PreAuthorize("hasAuthority('view_items_api')")
    public ItemDTO getItem(
            @PathVariable(name = "id") Long id
    ) {
        logger.debug("Executing Item API Controller method GET : getItem with id : {}", id);
        logger.info("API : Viewing item");
        SimpleItemDTO item = new SimpleItemDTO();
        item.setId(id);
        return itemService.findById(item).orElse(new ItemDTO());
    }

    @DeleteMapping(value = "/{id}")
    @PreAuthorize("hasAuthority('delete_item_api')")
    public ResponseEntity<APIResponse> deleteItem(
            @PathVariable(name = "id") Long id
    ) {
        logger.debug("Executing Item API Controller method DELETE : deleteItem with id : {}", id);
        logger.info("API : Deleting Item");
        SimpleItemDTO item = new SimpleItemDTO();
        item.setId(id);
        itemService.deleteById(item);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping(value = "/{id}")
    @PreAuthorize("hasAuthority('update_item_api')")
    public ResponseEntity<APIResponse> updateItem(
            @RequestBody ItemDTO item,
            @PathVariable("id") Long id
    ) {
        logger.debug("Executing Item API Controller method PUT : updateItem with id : {}", id);
        logger.info("API : Updating Item");
        item = fieldTrimmer.trim(item);
        item.setId(id);
        Set<String> errors = validator.validate(item);
        if (!errors.isEmpty()) {
            APIResponse response = new APIResponse();
            response.setMessage(APIResponseProperties.ITEM_NOT_UPDATED);
            response.setErrors(errors);
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        } else {
            itemService.update(item);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }
}
