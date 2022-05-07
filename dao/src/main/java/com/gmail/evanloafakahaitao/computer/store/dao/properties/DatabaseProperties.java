package com.gmail.evanloafakahaitao.computer.store.dao.properties;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class DatabaseProperties {

    @Value("${database.url}")
    private String databaseUrl;
    @Value("${database.username}")
    private String databaseUsername;
    @Value("${database.password}")
    private String databasePassword;
    @Value("${database.driver.name}")
    private String databaseDriverName;
    @Value("${hibernate.hbm2ddl.auto}")
    private String hibernateHBM2DDLAuto;
    @Value("${hibernate.show_sql}")
    private String hibernateShowSQL;
    @Value("${hibernate.dialect}")
    private String hibernateDialect;
    @Value("${hibernate.format_sql}")
    private String hibernateFormatSQL;
    @Value("${hibernate.cache.use_second_level_cache}")
    private String hibernateUseSecondLevelCache;
    @Value("${hibernate.cache.region.factory_class}")
    private String hibernateCacheRegionFactoryClass;
    @Value("${hibernate.current_session_context_class}")
    private String hibernateCurrentSessionContextClass;
    @Value("${hibernate.physical_naming_strategy}")
    private String hibernatePhysicalNamingStrategy;
    @Value("hibernate.storage.engine")
    private String hibernateStorageEngine;

    public String getDatabaseUrl() {
        return databaseUrl;
    }

    public String getDatabaseUsername() {
        return databaseUsername;
    }

    public String getDatabasePassword() {
        return databasePassword;
    }

    public String getDatabaseDriverName() {
        return databaseDriverName;
    }

    public String getHibernateHBM2DDLAuto() {
        return hibernateHBM2DDLAuto;
    }

    public String getHibernateShowSQL() {
        return hibernateShowSQL;
    }

    public String getHibernateDialect() {
        return hibernateDialect;
    }

    public String getHibernateFormatSQL() {
        return hibernateFormatSQL;
    }

    public String getHibernateUseSecondLevelCache() {
        return hibernateUseSecondLevelCache;
    }

    public String getHibernateCacheRegionFactoryClass() {
        return hibernateCacheRegionFactoryClass;
    }

    public String getHibernateCurrentSessionContextClass() {
        return hibernateCurrentSessionContextClass;
    }

    public String getHibernatePhysicalNamingStrategy() {
        return hibernatePhysicalNamingStrategy;
    }

    public String getHibernateStorageEngine() {
        return hibernateStorageEngine;
    }
}
