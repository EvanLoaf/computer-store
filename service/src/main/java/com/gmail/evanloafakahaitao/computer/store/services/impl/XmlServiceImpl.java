package com.gmail.evanloafakahaitao.computer.store.services.impl;

import com.gmail.evanloafakahaitao.computer.store.dao.XmlDao;
import com.gmail.evanloafakahaitao.computer.store.dao.impl.XmlDaoImpl;
import com.gmail.evanloafakahaitao.computer.store.services.XmlService;
import com.gmail.evanloafakahaitao.computer.store.services.xml.dto.CatalogXmlDTO;
import com.gmail.evanloafakahaitao.computer.store.services.xml.dto.ItemXmlDTO;
import com.gmail.evanloafakahaitao.computer.store.services.xml.util.XmlParser;
import com.gmail.evanloafakahaitao.computer.store.services.xml.util.XmlValidator;

import java.io.File;
import java.util.List;

public class XmlServiceImpl implements XmlService {

    private XmlDao xmlDao = new XmlDaoImpl();
    private XmlValidator xmlValidator = new XmlValidator();
    private XmlParser xmlParser = new XmlParser();

    @Override
    public List<ItemXmlDTO> getItems(String filePath, String schemaPath) {
        CatalogXmlDTO items = new CatalogXmlDTO();
        File xml = xmlDao.getFile(filePath);
        File schema = xmlDao.getFile(schemaPath);
        boolean isValid = xmlValidator.validate(xml, schema);
        System.out.printf("XML file is valid : %s%n", isValid);
        if (isValid) {
            System.out.println("Parsing XML file...");
            items = xmlParser.unmarshal(xml);
        }
        return items.getItems();
    }
}
