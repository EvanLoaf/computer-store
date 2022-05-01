package com.gmail.evanloafakahaitao.computer.store.dao.util;

import com.gmail.evanloafakahaitao.computer.store.dao.ItemDao;
import com.gmail.evanloafakahaitao.computer.store.dao.UserDao;
import com.gmail.evanloafakahaitao.computer.store.dao.connection.ConnectionService;
import com.gmail.evanloafakahaitao.computer.store.dao.impl.ItemDaoImpl;
import com.gmail.evanloafakahaitao.computer.store.dao.impl.UserDaoImpl;
import com.gmail.evanloafakahaitao.computer.store.dao.model.Item;
import com.gmail.evanloafakahaitao.computer.store.dao.model.Order;
import com.gmail.evanloafakahaitao.computer.store.dao.model.User;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;

public class OrderConverter {

    private ItemDao itemDao = new ItemDaoImpl();
    private UserDao userDao = new UserDaoImpl();

    public Order toOrder(ResultSet resultSet) {
        Long id = null;
        String orderCode = null;
        LocalDateTime created = null;
        Integer quantity = null;
        Item item = null;
        User user = null;
        try {
            id = resultSet.getLong("id");
            orderCode = resultSet.getString("order_code");
            created = (LocalDateTime) resultSet.getObject("created");
            quantity = resultSet.getInt("quantity");
            item = itemDao.findById(
                    ConnectionService.getInstance().getConnection(),
                    resultSet.getLong("item_id")
            );
            user = userDao.findById(
                    ConnectionService.getInstance().getConnection(),
                    resultSet.getLong("user_id")
            );
        } catch (SQLException e) {
            System.out.println("Error fetching Order fields from ResultSet");
            e.printStackTrace();
        }
        return Order.newBuilder()
                .withId(id)
                .withOrderCode(orderCode)
                .withCreated(created)
                .withQuantity(quantity)
                .withItem(item)
                .withUser(user)
                .build();
    }
}
