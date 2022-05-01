package com.gmail.evanloafakahaitao.computer.store.config;

import java.util.ResourceBundle;

public class ConfigurationManager {

    private static ConfigurationManager instance;

    private ResourceBundle resourceBundle;

    private static final String BUNDLE_NAME = "config";

    private ConfigurationManager() {}

    public static ConfigurationManager getInstance() {
        if (instance == null) {
            instance = new ConfigurationManager();
            instance.resourceBundle = ResourceBundle.getBundle(BUNDLE_NAME);
            System.out.println("Config Manager initialized");
        }
        return instance;
    }

    public String getProperty(String key) {
        System.out.printf("CM : Retrieving property %s%n", key);
        return (String) resourceBundle.getObject(key);
    }
}
