package com.gmail.evanloafakahaitao.computer.store.dao.impl;

import com.gmail.evanloafakahaitao.computer.store.dao.ItemDao;
import com.gmail.evanloafakahaitao.computer.store.dao.model.Item;
import com.gmail.evanloafakahaitao.computer.store.dao.util.ItemConverter;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ItemDaoImpl implements ItemDao {

    private ItemConverter itemConverter = new ItemConverter();

    @Override
    public int save(Connection connection, List<Item> items) throws SQLException {
        int changedRows = 0;
        String sql =
                "INSERT INTO \n" +
                "   item (name, vendor_code, price, description) \n" +
                "VALUES \n" +
                "   (?, ?, ?, ?);\n";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            for (Item item : items) {
                preparedStatement.setString(1, item.getName());
                preparedStatement.setString(2, item.getVendorCode());
                preparedStatement.setBigDecimal(3, item.getPrice());
                preparedStatement.setString(4, item.getDescription());
                preparedStatement.addBatch();
            }
            int[] changes = preparedStatement.executeBatch();
            for (int change : changes) {
                changedRows += change;
            }
        } catch (SQLException e) {
            System.out.println("Error saving Item into DB");
            e.printStackTrace();
            throw new SQLException(e);
        }
        return changedRows;
    }

    @Override
    public List<Item> findAll(Connection connection) {
        List<Item> items = new ArrayList<>();
        String sql =
                "SELECT \n" +
                "   * \n" +
                "FROM \n" +
                "   item;\n";
        try (
                PreparedStatement preparedStatement = connection.prepareStatement(sql);
                ResultSet resultSet = preparedStatement.executeQuery()
        ) {
            while (resultSet.next()) {
                Item item = itemConverter.toItem(resultSet);
                items.add(item);
            }
        } catch (SQLException e) {
            System.out.println("Error retrieving all Items from DB");
            e.printStackTrace();
        }
        return items;
    }

    @Override
    public Item findByVendorCode(Connection connection, String vendorCode) {
        Item item = null;
        String sql =
                "SELECT \n" +
                "   * \n" +
                "FROM \n" +
                "   item \n" +
                "WHERE \n" +
                "   item.vendor_code = ?;\n";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, vendorCode);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    item = itemConverter.toItem(resultSet);
                }
            }
        } catch (SQLException e) {
            System.out.println("Error retrieving Item by vendor code");
            e.printStackTrace();
        }
        return item;
    }

    @Override
    public Item findById(Connection connection, Long id) {
        Item item = null;
        String sql =
                "SELECT \n" +
                "   * \n" +
                "FROM \n" +
                "   item \n" +
                "WHERE \n" +
                "   item.id = ?;\n";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setLong(1, id);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    item = itemConverter.toItem(resultSet);
                }
            }
        } catch (SQLException e) {
            System.out.println("Error retrieving Item by id");
            e.printStackTrace();
        }
        return item;
    }
}
