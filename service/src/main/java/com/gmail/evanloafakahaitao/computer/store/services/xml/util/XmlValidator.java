package com.gmail.evanloafakahaitao.computer.store.services.xml.util;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;
import org.xml.sax.SAXException;

import javax.xml.XMLConstants;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import java.io.File;
import java.io.IOException;

@Component
public class XmlValidator {

    private static final Logger logger = LogManager.getLogger(XmlValidator.class);

    public boolean validate(File xmlFile, String schemaFile) {
        logger.info("Validating XML file against schema");
        SchemaFactory schemaFactory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
        try {
            Schema schema = schemaFactory.newSchema(new StreamSource(schemaFile));
            Validator validator = schema.newValidator();
            validator.validate(new StreamSource(xmlFile));
        } catch (SAXException e) {
            logger.error("XML validation against schema failed", e);
            return false;
        } catch (IOException e) {
            logger.error("Error accessing XML file for validation", e);
            return false;
        }
        return true;
    }
}
