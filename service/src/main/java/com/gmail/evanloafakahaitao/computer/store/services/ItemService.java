package com.gmail.evanloafakahaitao.computer.store.services;

import com.gmail.evanloafakahaitao.computer.store.dao.model.Item;
import com.gmail.evanloafakahaitao.computer.store.services.model.ItemXmlBinding;

import java.util.List;

public interface ItemService {

    int save(List<ItemXmlBinding> itemXmlBindings);

    List<Item> findAll();

    Item findByVendorCode(String vendorCode);
}
