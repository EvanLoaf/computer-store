package com.gmail.evanloafakahaitao.computer.store.dao;

import com.gmail.evanloafakahaitao.computer.store.dao.model.Item;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public interface ItemDao extends GenericDao<Item> {

    Item findByVendorCode(String vendorCode);

    List<Item> findInPriceRange(BigDecimal minPrice, BigDecimal maxPrice);
}
