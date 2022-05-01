package com.gmail.evanloafakahaitao.computer.store.services;

import com.gmail.evanloafakahaitao.computer.store.dao.model.Order;

import java.util.List;

public interface OrderService {

    int save(Order order);

    List<Order> findByUserId(Long id);

    int delete(String orderCode);
}
