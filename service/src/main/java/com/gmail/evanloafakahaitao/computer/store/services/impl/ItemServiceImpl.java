package com.gmail.evanloafakahaitao.computer.store.services.impl;

import com.gmail.evanloafakahaitao.computer.store.dao.ItemDao;
import com.gmail.evanloafakahaitao.computer.store.dao.connection.ConnectionService;
import com.gmail.evanloafakahaitao.computer.store.dao.impl.ItemDaoImpl;
import com.gmail.evanloafakahaitao.computer.store.dao.model.Item;
import com.gmail.evanloafakahaitao.computer.store.services.ItemService;
import com.gmail.evanloafakahaitao.computer.store.services.model.ItemXmlBinding;
import com.gmail.evanloafakahaitao.computer.store.services.util.ItemConverter;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class ItemServiceImpl implements ItemService {

    private ItemDao itemDao = new ItemDaoImpl();
    private ItemConverter itemConverter = new ItemConverter();

    @Override
    public int save(List<ItemXmlBinding> itemXmlBindings) {
        int changedRows = 0;
        Connection connection = ConnectionService.getInstance().getConnection();
        System.out.println("Saving items parsed from XML");
        List<Item> items = itemConverter.toItems(itemXmlBindings);
        try {
            connection.setAutoCommit(false);
            changedRows = itemDao.save(connection, items);
            connection.commit();
            System.out.printf("Received %d items, saved %d items%n", itemXmlBindings.size(), changedRows);
        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException exc) {
                System.out.println("Error rolling back item save transaction");
                exc.printStackTrace();
            }
            e.printStackTrace();
        } finally {
            try {
                connection.setAutoCommit(true);
            } catch (SQLException e) {
                System.out.println("Error setting connection auto commit to true");
                e.printStackTrace();
            }
        }
        return changedRows;
    }

    @Override
    public List<Item> findAll() {
        List<Item> items;
        Connection connection = ConnectionService.getInstance().getConnection();
        System.out.println("Retrieving all items");
        items = itemDao.findAll(connection);
        System.out.printf("Retrieved items : %d%n", items.size());
        return items;
    }

    @Override
    public Item findByVendorCode(String vendorCode) {
        Item item;
        Connection connection = ConnectionService.getInstance().getConnection();
        System.out.println("Retrieving item by vendor code");
        item = itemDao.findByVendorCode(connection, vendorCode);
        System.out.printf("Item with vendor code %s is found : %s", vendorCode, item != null);
        return item;
    }
}
