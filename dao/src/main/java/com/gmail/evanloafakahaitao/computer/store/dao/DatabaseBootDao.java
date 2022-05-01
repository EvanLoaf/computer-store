package com.gmail.evanloafakahaitao.computer.store.dao;

import java.sql.Connection;

public interface DatabaseBootDao {

    void executeBootFile(Connection connection, String filepath);
}
