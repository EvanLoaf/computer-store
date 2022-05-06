package com.gmail.evanloafakahaitao.computer.store.config.properties;

public interface HibernateProperties {

    String HIBERNATE_CURRENT_SESSION_CONTEXT_CLASS = "hibernate.current_session_context_class";
    String HIBERNATE_HBM2DDL_AUTO = "hibernate.hbm2ddl.auto";
    String HIBERNATE_DIALECT = "hibernate.dialect";
    String HIBERNATE_SHOW_SQL = "hibernate.show_sql";
    String HIBERNATE_FORMAT_SQL = "hibernate.format_sql";
    String HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE = "hibernate.cache.use_second_level_cache";
    String HIBERNATE_CACHE_REGION_FACTORY_CLASS = "hibernate.cache.region.factory_class";
    /*String HIBERNATE_JAVAX_CACHE_PROVIDER = "hibernate.javax.cache.provider";
    String HIBERNATE_JAVAX_CACHE_URI = "hibernate.javax.cache.uri";*/
    String HIBERNATE_PHYSICAL_NAMING_STRATEGY = "hibernate.physical_naming_strategy";
    String HIBERNATE_STORAGE_ENGINE = "hibernate.storage.engine";
}
