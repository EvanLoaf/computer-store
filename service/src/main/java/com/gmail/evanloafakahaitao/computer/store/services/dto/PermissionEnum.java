package com.gmail.evanloafakahaitao.computer.store.services.dto;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public enum PermissionEnum {

    SECURITY_ADMIN_BASIC_PERMISSION,
    API_ADMIN_BASIC_PERMISSION,
    SALES_ADMIN_BASIC_PERMISSION,
    USER_BASIC_PERMISSION;

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