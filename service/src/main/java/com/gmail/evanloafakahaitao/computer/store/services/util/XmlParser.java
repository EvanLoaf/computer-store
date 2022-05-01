package com.gmail.evanloafakahaitao.computer.store.services.util;

import com.gmail.evanloafakahaitao.computer.store.services.model.CatalogXmlBinding;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.File;

public class XmlParser {

    public CatalogXmlBinding unmarshal(File xml) {
        CatalogXmlBinding items = null;
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(CatalogXmlBinding.class);
            Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
            items = (CatalogXmlBinding) unmarshaller.unmarshal(xml);
        } catch (JAXBException e) {
            System.out.println("Error parsing XML");
            e.printStackTrace();
        }
        return items;
    }
}
