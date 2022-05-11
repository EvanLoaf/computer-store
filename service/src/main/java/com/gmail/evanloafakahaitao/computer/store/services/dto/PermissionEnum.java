package com.gmail.evanloafakahaitao.computer.store.services.dto;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public enum PermissionEnum {

    SECURITY_ADMIN_BASIC_PERMISSION,
    API_ADMIN_BASIC_PERMISSION,
    SALES_ADMIN_BASIC_PERMISSION,
    USER_BASIC_PERMISSION,
    VIEW_ITEMS,
    CREATE_ITEM,
    DELETE_ITEM,
    UPLOAD_ITEMS,
    VIEW_ORDERS_SELF,
    CREATE_ORDER,
    DELETE_ORDER_SELF,
    VIEW_ORDERS_ALL,
    UPDATE_ORDER_STATUS,
    VIEW_USER_SELF,
    UPDATE_USER_SELF,
    VIEW_USERS_ALL,
    UPDATE_USERS_ALL,
    DISABLE_USER,
    DELETE_USER,
    CREATE_USER,
    VIEW_NEWS,
    CREATE_NEWS,
    UPDATE_NEWS,
    DELETE_NEWS,
    CREATE_COMMENT,
    DELETE_COMMENTS,
    UPDATE_DISCOUNT_ITEM,
    UPDATE_DISCOUNT_USERS,
    CREATE_FEEDBACK,
    DELETE_FEEDBACK,
    VIEW_FEEDBACK,
    VIEW_ITEMS_API,
    CREATE_ITEM_API,
    UPDATE_ITEM_API,
    DELETE_ITEM_API,
    MANAGE_BUSINESS_CARD,
    MANAGE_BUSINESS_CARD_API;

    private static final Logger logger = LogManager.getLogger(PermissionEnum.class);

    public static PermissionEnum getPermission(String permission) {
        try {
            return PermissionEnum.valueOf(permission.toUpperCase());
        } catch (IllegalArgumentException e) {
            logger.error("Permission {} not found - DTO", permission.toUpperCase(), e);
        }
        return null;
    }

    @Override
    public String toString() {
        return super.toString().toLowerCase();
    }
}