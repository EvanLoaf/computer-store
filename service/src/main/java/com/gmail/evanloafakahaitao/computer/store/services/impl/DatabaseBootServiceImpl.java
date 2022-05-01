package com.gmail.evanloafakahaitao.computer.store.services.impl;

import com.gmail.evanloafakahaitao.computer.store.dao.DatabaseBootDao;
import com.gmail.evanloafakahaitao.computer.store.dao.connection.ConnectionService;
import com.gmail.evanloafakahaitao.computer.store.dao.impl.DatabaseBootDaoImpl;
import com.gmail.evanloafakahaitao.computer.store.services.DatabaseBootService;

import java.sql.Connection;
import java.sql.SQLException;

public class DatabaseBootServiceImpl implements DatabaseBootService {

    private DatabaseBootDao databaseBootDao = new DatabaseBootDaoImpl();

    @Override
    public void executeBootFile(String filepath) {
        Connection connection = ConnectionService.getInstance().getConnection();
        System.out.println("Executing DB boot procedure");
        try {
            connection.setAutoCommit(false);
            databaseBootDao.executeBootFile(connection, filepath);
            connection.commit();
        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException exc) {
                System.out.println("Error rolling back DB boot file execution");
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
    }
}
