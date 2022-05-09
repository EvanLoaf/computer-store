package com.gmail.evanloafakahaitao.computer.store.dao;

import com.gmail.evanloafakahaitao.computer.store.dao.model.Order;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public interface OrderDao extends GenericDao<Order> {

    List<Order> findByUserId(Long id, Integer firstResult, Integer maxResults);

    Order findByOrderCode(String orderCode);

    void deleteByOrderCode(String orderCode);

    Long countAllByUserId(Long id);
}
