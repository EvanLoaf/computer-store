package com.gmail.evanloafakahaitao.computer.store.config;

import com.gmail.evanloafakahaitao.computer.store.dao.model.*;
import com.gmail.evanloafakahaitao.computer.store.dao.properties.DatabaseProperties;
import com.gmail.evanloafakahaitao.computer.store.dao.naming.CustomPhysicalNamingStrategy;
import com.zaxxer.hikari.HikariDataSource;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.Properties;

import static org.hibernate.cfg.AvailableSettings.*;

@Configuration
@EnableTransactionManagement
public class DatabaseConfig {

    private final DatabaseProperties databaseProperties;

    @Autowired
    public DatabaseConfig(DatabaseProperties databaseProperties) {
        this.databaseProperties = databaseProperties;
    }

    @Bean
    public DataSource dataSource() {
        final HikariDataSource dataSource = new HikariDataSource();
        dataSource.setPoolName("Computer store connection pool");
        dataSource.setMaximumPoolSize(databaseProperties.getPoolMaxSize());
        dataSource.setDataSourceClassName(databaseProperties.getPoolDataSourceClass());
        dataSource.addDataSourceProperty("url", databaseProperties.getDatabaseUrl());
        dataSource.addDataSourceProperty("user", databaseProperties.getDatabaseUsername());
        dataSource.addDataSourceProperty("password", databaseProperties.getDatabasePassword());
        dataSource.addDataSourceProperty("cachePrepStmts", databaseProperties.getPoolCachePreparedStatements());
        dataSource.addDataSourceProperty("prepStmtCacheSize", databaseProperties.getPoolCachePreparedStatementsSize());
        dataSource.addDataSourceProperty("prepStmtCacheSqlLimit", databaseProperties.getPoolCachePreparedStatementsSQLLimit());
        dataSource.addDataSourceProperty("useServerPrepStmts", databaseProperties.getPoolUseServerPreparedStatements());
        return dataSource;
    }

    @Bean
    public LocalSessionFactoryBean getSessionFactory(DataSource dataSource) {
        LocalSessionFactoryBean factoryBean = new LocalSessionFactoryBean();
        factoryBean.setDataSource(dataSource);
        factoryBean.setPhysicalNamingStrategy(new CustomPhysicalNamingStrategy());
        Properties properties = new Properties();
        properties.put(DIALECT, databaseProperties.getHibernateDialect());
        properties.put(STORAGE_ENGINE, databaseProperties.getHibernateStorageEngine());
        properties.put(SHOW_SQL, databaseProperties.getHibernateShowSQL());
        properties.put(FORMAT_SQL, databaseProperties.getHibernateFormatSQL());
        properties.put(HBM2DDL_AUTO, databaseProperties.getHibernateHBM2DDLAuto());
        properties.put(USE_SECOND_LEVEL_CACHE, databaseProperties.getHibernateUseSecondLevelCache());
        properties.put(CACHE_REGION_FACTORY, databaseProperties.getHibernateCacheRegionFactoryClass());
        factoryBean.setHibernateProperties(properties);
        factoryBean.setAnnotatedClasses(
                OrderId.class,
                User.class,
                Role.class,
                Permission.class,
                Feedback.class,
                Profile.class,
                News.class,
                Comment.class,
                Item.class,
                Order.class,
                Discount.class,
                BusinessCard.class,
                SoftDeleteEntity.class,
                SoftDeleteAndDisableEntity.class
        );
        return factoryBean;
    }

    @Bean
    public HibernateTransactionManager getTransactionManager(SessionFactory sessionFactory) {
        HibernateTransactionManager transactionManager = new HibernateTransactionManager();
        transactionManager.setSessionFactory(sessionFactory);
        return transactionManager;
    }
}
