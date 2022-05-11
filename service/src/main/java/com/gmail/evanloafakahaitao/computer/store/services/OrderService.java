package com.gmail.evanloafakahaitao.computer.store.services;

import com.gmail.evanloafakahaitao.computer.store.services.dto.NewOrderDTO;
import com.gmail.evanloafakahaitao.computer.store.services.dto.OrderDTO;
import com.gmail.evanloafakahaitao.computer.store.services.dto.SimpleOrderDTO;

import java.util.List;

public interface OrderService {

    SimpleOrderDTO save(NewOrderDTO orderDTO);

    List<SimpleOrderDTO> findByCurrentUser(Integer firstResult, Integer maxResults);

    SimpleOrderDTO update(SimpleOrderDTO simpleOrderDTO);

    void deleteByOrderCode(SimpleOrderDTO orderDTO);

    List<OrderDTO> findAll(Integer firstResult, Integer maxResults);

    Long countAll();

    Long countAllByCurrentUser();
}
