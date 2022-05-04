package com.gmail.evanloafakahaitao.computer.store.services.util;

import com.gmail.evanloafakahaitao.computer.store.dao.model.Item;
import com.gmail.evanloafakahaitao.computer.store.services.model.ItemXmlBinding;

import java.util.ArrayList;
import java.util.List;

public class ItemConverter {

    public List<Item> toItems(List<ItemXmlBinding> itemXmlBindings) {
        List<Item> items = new ArrayList<>();
        if (itemXmlBindings != null && !itemXmlBindings.isEmpty()) {
            System.out.printf("Converting XML items, count : %d%n", itemXmlBindings.size());
            for (ItemXmlBinding itemXmlBinding : itemXmlBindings) {
                Item item = Item.newBuilder()
                        .withName(itemXmlBinding.getName())
                        .withVendorCode(itemXmlBinding.getVendorCode())
                        .withPrice(itemXmlBinding.getPrice())
                        .withDescription(itemXmlBinding.getDescription())
                        .build();
                items.add(item);
            }
        } else {
            System.out.println("No XML items to convert in the list");
        }
        return items;
    }
}
