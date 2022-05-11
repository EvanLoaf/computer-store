package com.gmail.evanloafakahaitao.computer.store.services.xml.util;

import com.gmail.evanloafakahaitao.computer.store.services.dto.ItemDTO;
import com.gmail.evanloafakahaitao.computer.store.services.xml.dto.ItemXmlDTO;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ItemXmlConverter {

    public List<ItemDTO> toItems(List<ItemXmlDTO> itemXmlList) {
        List<ItemDTO> items = new ArrayList<>();
        if (itemXmlList != null && !itemXmlList.isEmpty()) {
            System.out.printf("Converting XML items, count : %d%n", itemXmlList.size());
            for (ItemXmlDTO itemXmlDTO : itemXmlList) {
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
