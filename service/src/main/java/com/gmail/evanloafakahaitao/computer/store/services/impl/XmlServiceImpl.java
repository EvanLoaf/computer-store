package com.gmail.evanloafakahaitao.computer.store.services.impl;

import com.gmail.evanloafakahaitao.computer.store.dao.XmlDao;
import com.gmail.evanloafakahaitao.computer.store.services.XmlService;
import com.gmail.evanloafakahaitao.computer.store.services.xml.dto.CatalogXmlDTO;
import com.gmail.evanloafakahaitao.computer.store.services.xml.dto.ItemXmlDTO;
import com.gmail.evanloafakahaitao.computer.store.services.xml.util.XmlParser;
import com.gmail.evanloafakahaitao.computer.store.services.xml.util.XmlValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.List;

@Service
public class XmlServiceImpl implements XmlService {

    private final XmlDao xmlDao;
    private final XmlValidator xmlValidator;
    private final XmlParser xmlParser;

    @Autowired
    public XmlServiceImpl(
            XmlDao xmlDao,
            XmlValidator xmlValidator,
            XmlParser xmlParser
    ) {
        this.xmlDao = xmlDao;
        this.xmlValidator = xmlValidator;
        this.xmlParser = xmlParser;
    }

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
