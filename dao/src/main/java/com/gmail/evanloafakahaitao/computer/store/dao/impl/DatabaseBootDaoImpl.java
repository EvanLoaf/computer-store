package com.gmail.evanloafakahaitao.computer.store.dao.impl;

import com.gmail.evanloafakahaitao.computer.store.dao.DatabaseBootDao;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseBootDaoImpl implements DatabaseBootDao {

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
            System.out.printf("Executed SQL boot file : %s%n", filepath);
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
