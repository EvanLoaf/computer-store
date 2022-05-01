package com.gmail.evanloafakahaitao.computer.store.dao;

import com.gmail.evanloafakahaitao.computer.store.dao.model.Item;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public interface ItemDao {

    int save(Connection connection, List<Item> items) throws SQLException;

    List<Item> findAll(Connection connection);

    Item findByVendorCode(Connection connection, String vendorCode);

    Item findById(Connection connection, Long id);
}
