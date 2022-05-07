package com.gmail.evanloafakahaitao.computer.store.services.xml.util;

import org.xml.sax.SAXException;

import javax.xml.XMLConstants;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import java.io.File;
import java.io.IOException;

public class XmlValidator {

    public boolean validate(File xmlFile, File schemaFile) {
        SchemaFactory schemaFactory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
        try {
            Schema schema = schemaFactory.newSchema(schemaFile);
            Validator validator = schema.newValidator();
            validator.validate(new StreamSource(xmlFile));
        } catch (SAXException e) {
            System.out.println("XML validation against schema failed");
            e.printStackTrace();
            return false;
        } catch (IOException e) {
            System.out.println("Error accessing XML file for validation");
            e.printStackTrace();
            return false;
        }
        return true;
    }
}
