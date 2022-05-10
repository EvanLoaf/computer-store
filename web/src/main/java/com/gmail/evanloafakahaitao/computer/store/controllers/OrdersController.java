package com.gmail.evanloafakahaitao.computer.store.controllers;

import com.gmail.evanloafakahaitao.computer.store.controllers.model.PaginationDetails;
import com.gmail.evanloafakahaitao.computer.store.controllers.properties.PageProperties;
import com.gmail.evanloafakahaitao.computer.store.controllers.properties.WebProperties;
import com.gmail.evanloafakahaitao.computer.store.controllers.util.PaginationUtil;
import com.gmail.evanloafakahaitao.computer.store.services.ItemService;
import com.gmail.evanloafakahaitao.computer.store.services.OrderService;
import com.gmail.evanloafakahaitao.computer.store.services.UserService;
import com.gmail.evanloafakahaitao.computer.store.services.dto.*;
import com.gmail.evanloafakahaitao.computer.store.services.exceptions.ItemNotFoundException;
import com.gmail.evanloafakahaitao.computer.store.services.util.CurrentUserUtil;
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

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping(WebProperties.PUBLIC_ENTRY_POINT_PREFIX + "/orders")
public class OrdersController {

    private static final Logger logger = LogManager.getLogger(OrdersController.class);

    private final PageProperties pageProperties;
    private final OrderService orderService;
    private final UserService userService;
    private final ItemService itemService;
    private final PaginationUtil paginationUtil;
    private final Validator orderValidator;

    @Autowired
    public OrdersController(
            PageProperties pageProperties,
            OrderService orderService,
            UserService userService,
            ItemService itemService,
            PaginationUtil paginationUtil,
            @Qualifier("orderValidator") Validator orderValidator
    ) {
        this.pageProperties = pageProperties;
        this.orderService = orderService;
        this.userService = userService;
        this.itemService = itemService;
        this.paginationUtil = paginationUtil;
        this.orderValidator = orderValidator;
    }

    @GetMapping
    @PreAuthorize("hasAuthority('view_orders_self')")
    public String getOrdersFromUser(
            @RequestParam(value = "page", defaultValue = "1") Integer page,
            ModelMap modelMap
    ) {
        logger.debug("Executing Orders Controller method GET : getOrdersFromUser, page : {}", page);
        logger.info("Retrieving Orders by User");
        List<SimpleOrderDTO> orders = orderService.findByCurrentUser(
                paginationUtil.getStartPosition(page),
                pageProperties.getPaginationMaxResults()
        );
        modelMap.addAttribute("orders", orders);
        PaginationDetails pagination = new PaginationDetails();
        pagination.setPage(page);
        pagination.setPageNumbers(
                paginationUtil.getPageNumbers(orderService.countAll().intValue())
        );
        pagination.setStartPosition(paginationUtil.getPageNumerationStart(page));
        modelMap.addAttribute("pagination", pagination);
        return pageProperties.getOrdersPagePath();
    }

    @GetMapping(value = "/admin")
    @PreAuthorize("hasAuthority('view_orders_all')")
    public String getOrders(
            @RequestParam(value = "page", defaultValue = "1") Integer page,
            ModelMap modelMap
    ) {
        logger.debug("Executing Orders Controller method GET : getOrders, page : {}", page);
        logger.info("Retrieving all Orders");
        List<OrderDTO> orders = orderService.findAll(
                paginationUtil.getStartPosition(page),
                pageProperties.getPaginationMaxResults()
        );
        modelMap.addAttribute("statusEnum", OrderStatusEnum.values());
        modelMap.addAttribute("orders", orders);
        PaginationDetails pagination = new PaginationDetails();
        pagination.setPage(page);
        pagination.setPageNumbers(
                paginationUtil.getPageNumbers(userService.countAllNotDeleted().intValue())
        );
        pagination.setStartPosition(paginationUtil.getPageNumerationStart(page));
        modelMap.addAttribute("pagination", pagination);
        return pageProperties.getOrdersPagePath();
    }

    @PostMapping
    @PreAuthorize("hasAuthority('create_order')")
    public String createOrder(
            @ModelAttribute("order") NewOrderDTO order,
            BindingResult result,
            ModelMap modelMap
    ) {
        logger.debug("Executing Orders Controller method POST : createOrder");
        logger.info("Creating Order");
        orderValidator.validate(order, result);
        if (result.hasErrors()) {
            SimpleItemDTO itemDTO = new SimpleItemDTO();
            itemDTO.setVendorCode(order.getItem().getVendorCode());
            Optional<ItemDTO> item = itemService.findByVendorCode(itemDTO);
            modelMap.addAttribute(
                    "item",
                    item.orElseThrow(() -> new ItemNotFoundException("The Item you're trying to order is unavailable"))
            );
            SimpleUserDTO userDTO = new SimpleUserDTO();
            userDTO.setId(CurrentUserUtil.getCurrentId());
            UserDTO user = userService.findById(userDTO);
            modelMap.addAttribute("user", user);
            modelMap.addAttribute("order", order);
            return pageProperties.getOrderCreatePagePath();
        } else {
            orderService.save(order);
            return "redirect:" + WebProperties.PUBLIC_ENTRY_POINT_PREFIX + "/orders";
        }
    }

    @GetMapping(value = "/items/{id}")
    @PreAuthorize("hasAuthority('create_order')")
    public String createOrderPage(
            @PathVariable("id") Long itemId,
            ModelMap modelMap
    ) {
        logger.debug("Executing Orders Controller method GET : createOrderPage");
        logger.info("Entering Order creation page");
        SimpleItemDTO itemDTO = new SimpleItemDTO();
        itemDTO.setId(itemId);
        Optional<ItemDTO> item = itemService.findById(itemDTO);
        modelMap.addAttribute(
                "item",
                item.orElseThrow(() -> new ItemNotFoundException("The Item you're trying to order is unavailable"))
        );
        SimpleUserDTO userDTO = new SimpleUserDTO();
        userDTO.setId(CurrentUserUtil.getCurrentId());
        UserDTO user = userService.findById(userDTO);
        modelMap.addAttribute("user", user);
        NewOrderDTO order = new NewOrderDTO();
        modelMap.addAttribute("order", order);
        return pageProperties.getOrderCreatePagePath();
    }

    @PostMapping(value = "/{orderCode}")
    @PreAuthorize("hasAuthority('update_order_status')")
    public String updateOrder(
            @PathVariable("orderCode") String orderCode,
            @RequestParam("status") OrderStatusEnum status
    ) {
        logger.debug("Executing Orders Controller method POST : updateOrder to status : {}", status.toString());
        logger.info("Updating Order status");
        SimpleOrderDTO order = new SimpleOrderDTO();
        order.setOrderCode(orderCode);
        order.setStatus(status);
        orderService.update(order);
        return "redirect:" + WebProperties.PUBLIC_ENTRY_POINT_PREFIX + "/orders/admin" + "?status=true";
    }

    @GetMapping(value = "/{orderCode}/delete")
    @PreAuthorize("hasAuthority('delete_order_self')")
    public String deleteOrder(
            @PathVariable("orderCode") String orderCode
    ) {
        logger.debug("Executing Orders Controller method : deleteOrder with order code : {} ", orderCode);
        logger.info("Deleting Order");
        SimpleOrderDTO orderDTO = new SimpleOrderDTO();
        orderDTO.setOrderCode(orderCode);
        orderService.deleteByOrderCode(orderDTO);
        return "redirect:" + WebProperties.PUBLIC_ENTRY_POINT_PREFIX + "/orders";
    }
}
