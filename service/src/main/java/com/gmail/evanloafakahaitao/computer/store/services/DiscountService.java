package com.gmail.evanloafakahaitao.computer.store.services;

import com.gmail.evanloafakahaitao.computer.store.services.dto.DiscountDTO;

import java.util.List;

public interface DiscountService {

    List<DiscountDTO> findAll();
}
