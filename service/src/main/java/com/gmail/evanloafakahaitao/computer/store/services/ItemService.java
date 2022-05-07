package com.gmail.evanloafakahaitao.computer.store.services;

import com.gmail.evanloafakahaitao.computer.store.services.dto.ItemDTO;
import com.gmail.evanloafakahaitao.computer.store.services.dto.SimpleItemDTO;

import java.util.List;

public interface ItemService {

    List<ItemDTO> save(List<ItemDTO> itemDTOList);

    ItemDTO save(ItemDTO itemDTO);

    List<ItemDTO> findAll();

    ItemDTO findByVendorCode(SimpleItemDTO itemDTO);

    ItemDTO findById(SimpleItemDTO itemDTO);
}
