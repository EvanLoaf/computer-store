package com.gmail.evanloafakahaitao.computer.store.services.xml.util;

import com.gmail.evanloafakahaitao.computer.store.services.xml.dto.CatalogXmlDTO;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.File;

@Component
public class XmlParser {

    private static final Logger logger = LogManager.getLogger(XmlParser.class);

    public CatalogXmlDTO unmarshal(File xml) {
        logger.info("Unmarshalling Items from XML");
        CatalogXmlDTO items = null;
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(CatalogXmlDTO.class);
            Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
            items = (CatalogXmlDTO) unmarshaller.unmarshal(xml);
        } catch (JAXBException e) {
            logger.error("Error parsing XML", e);
        }
        return items;
    }
}
