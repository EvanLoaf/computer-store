package com.gmail.evanloafakahaitao.computer.store.services;

import com.gmail.evanloafakahaitao.computer.store.services.dto.NewOrderDTO;
import com.gmail.evanloafakahaitao.computer.store.services.dto.OrderDTO;
import com.gmail.evanloafakahaitao.computer.store.services.dto.SimpleOrderDTO;
import com.gmail.evanloafakahaitao.computer.store.services.dto.SimpleUserDTO;

import java.util.List;

public interface OrderService {

    SimpleOrderDTO save(NewOrderDTO orderDTO);

    List<SimpleOrderDTO> findByUserId(SimpleUserDTO userDTO);

    void deleteByOrderCode(SimpleOrderDTO orderDTO);

    List<OrderDTO> findAll();
}
