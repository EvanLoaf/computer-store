package com.gmail.evanloafakahaitao.computer.store.dao.model;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public enum OrderStatusEnum {

    NEW,
    PROCESSING,
    IN_PROGRESS,
    DELIVERED;

    private static final Logger logger = LogManager.getLogger(OrderStatusEnum.class);

    public static OrderStatusEnum getStatus(String status) {
        try {
            return OrderStatusEnum.valueOf(status.toUpperCase());
        } catch (IllegalArgumentException e) {
            logger.error("Order status {} not found - Entity", status.toUpperCase(), e);
        }
        return null;
    }


    @Override
    public String toString() {
        return super.toString().toLowerCase();
    }
}
