package com.gmail.evanloafakahaitao.computer.store.controllers.properties;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class PageProperties {

    @Value("${register.page.path}")
    private String registerPagePath;
    @Value("${login.page.path}")
    private String loginPagePath;
    @Value("${items.page.path}")
    private String itemsPagePath;
    @Value("${users.page.path}")
    private String usersPagePath;
    @Value("${user.update.page.path}")
    private String userUpdatePagePath;
    @Value("${orders.page.path}")
    private String ordersPagePath;
    @Value("${order.create.page.path}")
    private String orderCreatePagePath;
    @Value("${error.page.path}")
    private String errorsPagePath;

    public String getRegisterPagePath() {
        return registerPagePath;
    }

    public String getLoginPagePath() {
        return loginPagePath;
    }

    public String getItemsPagePath() {
        return itemsPagePath;
    }

    public String getUsersPagePath() {
        return usersPagePath;
    }

    public String getUserUpdatePagePath() {
        return userUpdatePagePath;
    }

    public String getOrdersPagePath() {
        return ordersPagePath;
    }

    public String getOrderCreatePagePath() {
        return orderCreatePagePath;
    }

    public String getErrorsPagePath() {
        return errorsPagePath;
    }
}
