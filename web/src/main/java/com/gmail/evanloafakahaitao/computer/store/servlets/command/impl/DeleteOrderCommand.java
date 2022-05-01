package com.gmail.evanloafakahaitao.computer.store.servlets.command.impl;

import com.gmail.evanloafakahaitao.computer.store.config.ConfigurationManager;
import com.gmail.evanloafakahaitao.computer.store.config.properties.PageProperties;
import com.gmail.evanloafakahaitao.computer.store.services.OrderService;
import com.gmail.evanloafakahaitao.computer.store.services.impl.OrderServiceImpl;
import com.gmail.evanloafakahaitao.computer.store.servlets.command.Command;
import com.gmail.evanloafakahaitao.computer.store.servlets.model.CommandEnum;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class DeleteOrderCommand implements Command {

    private OrderService orderService = new OrderServiceImpl();
    private ConfigurationManager configurationManager = ConfigurationManager.getInstance();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String orderCode = request.getParameter("order_code");
        int changedRows = orderService.delete(orderCode);
        if (changedRows == 1) {
            response.sendRedirect(request.getContextPath() + CommandEnum.ORDERS.getUrl());
        } else {
            request.setAttribute("error", String.format("Could not delete order %s%n", orderCode));
            return configurationManager.getProperty(PageProperties.ORDERS_PAGE_PATH);
        }
        return null;
    }
}
