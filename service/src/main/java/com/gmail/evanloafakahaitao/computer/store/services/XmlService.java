package com.gmail.evanloafakahaitao.computer.store.services;

import com.gmail.evanloafakahaitao.computer.store.services.xml.dto.ItemXmlDTO;

import java.util.List;

public interface XmlService {

    List<ItemXmlDTO> getItems(String filePath, String schemaPath);
}
