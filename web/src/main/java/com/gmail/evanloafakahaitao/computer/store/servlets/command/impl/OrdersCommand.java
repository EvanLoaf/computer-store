package com.gmail.evanloafakahaitao.computer.store.servlets.command.impl;

import com.gmail.evanloafakahaitao.computer.store.config.ConfigurationManager;
import com.gmail.evanloafakahaitao.computer.store.config.properties.PageProperties;
import com.gmail.evanloafakahaitao.computer.store.dao.model.Order;
import com.gmail.evanloafakahaitao.computer.store.services.OrderService;
import com.gmail.evanloafakahaitao.computer.store.services.impl.OrderServiceImpl;
import com.gmail.evanloafakahaitao.computer.store.servlets.command.Command;
import com.gmail.evanloafakahaitao.computer.store.servlets.model.UserPrincipal;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

public class OrdersCommand implements Command {

    private OrderService orderService = new OrderServiceImpl();
    private ConfigurationManager configurationManager = ConfigurationManager.getInstance();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession();
        UserPrincipal userPrincipal = (UserPrincipal) session.getAttribute("user");
        List<Order> orders = orderService.findByUserId(userPrincipal.getId());
        if (!orders.isEmpty()) {
            request.setAttribute("orders", orders);
        } else {
            request.setAttribute("error", "You haven't ordered anything so far");
        }
        return configurationManager.getProperty(PageProperties.ORDERS_PAGE_PATH);
    }
}
