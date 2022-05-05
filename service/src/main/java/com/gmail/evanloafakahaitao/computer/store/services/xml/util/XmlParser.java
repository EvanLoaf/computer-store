package com.gmail.evanloafakahaitao.computer.store.services.xml.util;

import com.gmail.evanloafakahaitao.computer.store.services.xml.dto.CatalogXmlDTO;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.File;

public class XmlParser {

    public CatalogXmlDTO unmarshal(File xml) {
        CatalogXmlDTO items = null;
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(CatalogXmlDTO.class);
            Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
            items = (CatalogXmlDTO) unmarshaller.unmarshal(xml);
        } catch (JAXBException e) {
            System.out.println("Error parsing XML");
            e.printStackTrace();
        }
        return items;
    }
}
