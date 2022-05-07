package com.gmail.evanloafakahaitao.computer.store.services.properties;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class XmlProperties {

    @Value("schema.file.path")
    private String schemaFilePath;

    public String getSchemaFilePath() {
        return schemaFilePath;
    }
}
