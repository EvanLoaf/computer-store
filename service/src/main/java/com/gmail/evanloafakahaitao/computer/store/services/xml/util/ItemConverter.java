package com.gmail.evanloafakahaitao.computer.store.services.xml.util;

import com.gmail.evanloafakahaitao.computer.store.dao.model.Item;
import com.gmail.evanloafakahaitao.computer.store.services.dto.ItemDTO;
import com.gmail.evanloafakahaitao.computer.store.services.xml.dto.ItemXmlDTO;

import java.util.ArrayList;
import java.util.List;

public class ItemConverter {

    public List<ItemDTO> toItems(List<ItemXmlDTO> itemXmlList) {
        List<ItemDTO> items = new ArrayList<>();
        if (itemXmlList != null && !itemXmlList.isEmpty()) {
            System.out.printf("Converting XML items, count : %d%n", itemXmlList.size());
            for (ItemXmlDTO itemXmlDTO : itemXmlList) {
                //TODO should be ItemDTO
                ItemDTO item = new ItemDTO();
                item.setName(itemXmlDTO.getName());
                item.setVendorCode(itemXmlDTO.getVendorCode());
                item.setPrice(itemXmlDTO.getPrice());
                item.setDescription(itemXmlDTO.getDescription());
                items.add(item);
            }
        } else {
            System.out.println("No XML items to convert in the list");
        }
        return items;
    }
}
