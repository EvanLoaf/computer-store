package com.gmail.evanloafakahaitao.computer.store.dao.util;

import com.gmail.evanloafakahaitao.computer.store.dao.model.Item;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ItemConverter {

    public Item toItem(ResultSet resultSet) {
        Long id = null;
        String name = null;
        String vendorCode = null;
        String description = null;
        BigDecimal price = null;
        try {
            id = resultSet.getLong("id");
            name = resultSet.getString("name");
            vendorCode = resultSet.getString("vendor_code");
            description = resultSet.getString("description");
            price = resultSet.getBigDecimal("price");
        } catch (SQLException e) {
            System.out.println("Error fetching Item fields from Result Set");
            e.printStackTrace();
        }
        return Item.newBuilder()
                .withId(id)
                .withName(name)
                .withVendorCode(vendorCode)
                .withDescription(description)
                .withPrice(price)
                .build();
    }
}
