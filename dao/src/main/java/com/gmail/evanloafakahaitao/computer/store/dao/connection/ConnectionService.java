package com.gmail.evanloafakahaitao.computer.store.dao.connection;

import com.gmail.evanloafakahaitao.computer.store.config.ConfigurationManager;
import com.gmail.evanloafakahaitao.computer.store.config.properties.DatabaseProperties;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionService {

    private Connection connection;

    private ConnectionService() {
        ConfigurationManager configurationManager = ConfigurationManager.getInstance();
        try {
            Class.forName(configurationManager.getProperty(DatabaseProperties.DATABASE_DRIVER_NAME));
            System.out.println("MySQL JDBC driver was successfully loaded");
        } catch (ClassNotFoundException e) {
            System.out.println("MySQL JDBC driver loading failed");
            e.printStackTrace();
        }
    }

    private static class Holder {
        private static final ConnectionService connectionService = new ConnectionService();
    }

    public static ConnectionService getInstance() {
        return Holder.connectionService;
    }

    public Connection getConnection() {
        ConfigurationManager configurationManager = ConfigurationManager.getInstance();
        if (connection != null) {
            return connection;
        } else {
            System.out.println("Establishing DB connection...");
            try {
                connection = DriverManager.getConnection(
                        configurationManager.getProperty(DatabaseProperties.DATABASE_URL),
                        configurationManager.getProperty(DatabaseProperties.DATABASE_USERNAME),
                        configurationManager.getProperty(DatabaseProperties.DATABASE_PASSWORD)
                );
                System.out.println("DB Connection established!");
                return connection;
            } catch (SQLException e) {
                System.out.println("DB Connection failure. Printing trace.");
                e.printStackTrace();
                throw new RuntimeException("Connection failure");
            }
        }
    }

    public void closeConnection() {
        if (connection != null) {
            try {
                connection.close();
                System.out.println("DB Connection closed");
            } catch (SQLException e) {
                System.out.println("Closing DB connection failed");
                e.printStackTrace();
            }
        }
    }
}
