package com.gmail.evanloafakahaitao.computer.store.services.impl;

import com.gmail.evanloafakahaitao.computer.store.dao.OrderDao;
import com.gmail.evanloafakahaitao.computer.store.dao.connection.ConnectionService;
import com.gmail.evanloafakahaitao.computer.store.dao.impl.OrderDaoImpl;
import com.gmail.evanloafakahaitao.computer.store.dao.model.Order;
import com.gmail.evanloafakahaitao.computer.store.services.OrderService;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.UUID;

public class OrderServiceImpl implements OrderService {

    private OrderDao orderDao = new OrderDaoImpl();

    @Override
    public int save(Order order) {
        int changedRows = 0;
        Connection connection = ConnectionService.getInstance().getConnection();
        System.out.println("Saving order");
        Order orderWithCode = Order.newBuilder()
                .withItem(order.getItem())
                .withUser(order.getUser())
                .withQuantity(order.getQuantity())
                .withOrderCode(
                        UUID.randomUUID().toString()
                ).build();
        try {
            connection.setAutoCommit(false);
            changedRows = orderDao.save(connection, orderWithCode);
            connection.commit();
            System.out.printf("Saved order : %s%n", changedRows == 1);
        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException exc) {
                System.out.println("Error rolling back order save transaction");
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
    public List<Order> findByUserId(Long id) {
        List<Order> orders;
        Connection connection = ConnectionService.getInstance().getConnection();
        System.out.println("Retrieving orders by User id");
        orders = orderDao.findByUserId(connection, id);
        System.out.printf("Retrieved %s orders for User id %d%n", (orders != null) ? orders.size() : null, id);
        return orders;
    }

    @Override
    public int delete(String orderCode) {
        int changedRows = 0;
        Connection connection = ConnectionService.getInstance().getConnection();
        System.out.println("Deleting order");
        try {
            connection.setAutoCommit(false);
            changedRows = orderDao.delete(connection, orderCode);
            connection.commit();
            System.out.printf("Deleted order with code %s : %s%n", orderCode, changedRows == 1);
        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException exc) {
                System.out.println("Error rolling back order deletion transaction");
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
}
