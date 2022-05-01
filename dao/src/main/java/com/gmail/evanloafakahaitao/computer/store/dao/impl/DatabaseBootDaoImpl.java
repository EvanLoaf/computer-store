package com.gmail.evanloafakahaitao.computer.store.dao.impl;

import com.gmail.evanloafakahaitao.computer.store.dao.DatabaseBootDao;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseBootDaoImpl implements DatabaseBootDao {

    private static final Logger logger = LogManager.getLogger(DatabaseBootDaoImpl.class);

    @Override
    public void executeBootFile(Connection connection, String filepath) {
        try (
                BufferedReader br = new BufferedReader(new FileReader(filepath));
                Statement statement = connection.createStatement()
        ) {
            StringBuilder sb = new StringBuilder();
            String line;
            boolean comment = false;
            while ((line = br.readLine()) != null) {
                if (line.contains("/*")) {
                    comment = true;
                    continue;
                }
                if (line.contains("*/")) {
                    comment = false;
                    continue;
                }
                if (!comment && !line.contains(";")) {
                    sb.append(line);
                } else if (!comment && line.contains(";")) {
                    sb.append(line);
                    statement.execute(sb.toString());
                    sb.setLength(0);
                }
            }
            logger.info("Executed SQL boot file : {}", filepath);
        } catch (FileNotFoundException e) {
            System.out.println("SQL boot file not found");
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("Error reading SQL boot file");
            e.printStackTrace();
        } catch (SQLException e) {
            System.out.println("Error executing SQL boot file");
            e.printStackTrace();
        }
    }
}
