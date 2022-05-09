package com.gmail.evanloafakahaitao.computer.store.servlets.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class ServletListener implements ServletContextListener {

    /*private DatabaseBootService databaseBootService = new DatabaseBootServiceImpl();*/
    private ConfigurationManager configurationManager = ConfigurationManager.getInstance();

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        System.out.println("App start-up, trying to run DB boot file");
        /*databaseBootService.executeBootFile(configurationManager.getProperty(FileProperties.SQL_BOOT_FILE_PATH));*/
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        /*ConnectionService.getInstance().closeConnection();*/
        System.out.println("App shutdown");
    }
}
