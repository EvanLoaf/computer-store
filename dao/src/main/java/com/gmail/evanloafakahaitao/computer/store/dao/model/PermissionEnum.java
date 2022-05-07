package com.gmail.evanloafakahaitao.computer.store.dao.model;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public enum PermissionEnum {

    USER_BASIC_PERMISSION;

    private static final Logger logger = LogManager.getLogger(PermissionEnum.class);

    public static PermissionEnum getPermission(String permission) {
        try {
            return PermissionEnum.valueOf(permission.toUpperCase());
        } catch (IllegalArgumentException e) {
            logger.error("Permission {} not found - Entity", permission.toUpperCase(), e);
        }
        return null;
    }

    @Override
    public String toString() {
        return super.toString().toLowerCase();
    }
}
