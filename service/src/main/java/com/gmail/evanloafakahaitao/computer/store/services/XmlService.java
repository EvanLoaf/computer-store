package com.gmail.evanloafakahaitao.computer.store.services;

import com.gmail.evanloafakahaitao.computer.store.services.model.ItemXmlBinding;

import java.util.List;

public interface XmlService {

    List<ItemXmlBinding> getItems(String filePath, String schemaPath);
}
