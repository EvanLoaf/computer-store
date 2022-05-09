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
    @Value("${hibernate.storage.engine}")
    private String hibernateStorageEngine;
    @Value("${pool.data.source.class}")
    private String poolDataSourceClass;
    @Value("${pool.max.size}")
    private Integer poolMaxSize;
    @Value("${pool.cache.prepared.statements}")
    private String poolCachePreparedStatements;
    @Value("${pool.cache.prepared.statements.size}")
    private Integer poolCachePreparedStatementsSize;
    @Value("${pool.cache.prepared.statements.sql.limit}")
    private Integer poolCachePreparedStatementsSQLLimit;
    @Value("${pool.use.server.prepared.statements}")
    private String poolUseServerPreparedStatements;

    public String getDatabaseUrl() {
        return databaseUrl;
    }

    public String getDatabaseUsername() {
        return databaseUsername;
    }

    public String getDatabasePassword() {
        return databasePassword;
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

    public String getHibernateStorageEngine() {
        return hibernateStorageEngine;
    }

    public String getPoolDataSourceClass() {
        return poolDataSourceClass;
    }

    public Integer getPoolMaxSize() {
        return poolMaxSize;
    }

    public String getPoolCachePreparedStatements() {
        return poolCachePreparedStatements;
    }

    public Integer getPoolCachePreparedStatementsSize() {
        return poolCachePreparedStatementsSize;
    }

    public Integer getPoolCachePreparedStatementsSQLLimit() {
        return poolCachePreparedStatementsSQLLimit;
    }

    public String getPoolUseServerPreparedStatements() {
        return poolUseServerPreparedStatements;
    }
}
