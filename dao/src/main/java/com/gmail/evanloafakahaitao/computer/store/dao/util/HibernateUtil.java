package com.gmail.evanloafakahaitao.computer.store.dao.util;

import com.gmail.evanloafakahaitao.computer.store.dao.model.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Environment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class HibernateUtil {

    private static final Logger logger = LogManager.getLogger(HibernateUtil.class);

    private static StandardServiceRegistry registry;
    private static SessionFactory sessionFactory;

    private final com.gmail.evanloafakahaitao.computer.store.dao.properties.DatabaseProperties databaseProperties;

    @Autowired
    private HibernateUtil(com.gmail.evanloafakahaitao.computer.store.dao.properties.DatabaseProperties databaseProperties) {
        this.databaseProperties = databaseProperties;
    }

    public SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            try {
                StandardServiceRegistryBuilder registryBuilder = new StandardServiceRegistryBuilder();

                Map<String, String> settings = new HashMap<>();
                settings.put(Environment.DRIVER, databaseProperties.getDatabaseDriverName());
                settings.put(Environment.URL, databaseProperties.getDatabaseUrl());
                settings.put(Environment.USER, databaseProperties.getDatabaseUsername());
                settings.put(Environment.PASS, databaseProperties.getDatabasePassword());
                settings.put(Environment.HBM2DDL_AUTO, databaseProperties.getHibernateHBM2DDLAuto());
                settings.put(Environment.CURRENT_SESSION_CONTEXT_CLASS, databaseProperties.getHibernateCurrentSessionContextClass());
                settings.put(Environment.USE_SECOND_LEVEL_CACHE, databaseProperties.getHibernateUseSecondLevelCache());
                settings.put(Environment.CACHE_REGION_FACTORY, databaseProperties.getHibernateCacheRegionFactoryClass());
                settings.put(Environment.SHOW_SQL, databaseProperties.getHibernateShowSQL());
                settings.put(Environment.PHYSICAL_NAMING_STRATEGY, databaseProperties.getHibernatePhysicalNamingStrategy());
                settings.put(Environment.FORMAT_SQL, databaseProperties.getHibernateFormatSQL());
                settings.put(Environment.DIALECT, databaseProperties.getHibernateDialect());
                settings.put(Environment.STORAGE_ENGINE, databaseProperties.getHibernateStorageEngine());

                registryBuilder.applySettings(settings);
                registry = registryBuilder.build();
                logger.info("Hibernate Registry builder created");

                MetadataSources sources = new MetadataSources(registry)
                        .addAnnotatedClass(OrderId.class)
                        .addAnnotatedClass(User.class)
                        .addAnnotatedClass(Role.class)
                        .addAnnotatedClass(Permission.class)
                        .addAnnotatedClass(Feedback.class)
                        .addAnnotatedClass(Profile.class)
                        .addAnnotatedClass(News.class)
                        .addAnnotatedClass(Comment.class)
                        .addAnnotatedClass(Item.class)
                        .addAnnotatedClass(Order.class)
                        .addAnnotatedClass(Discount.class);
                Metadata metadata = sources.getMetadataBuilder().build();
                sessionFactory = metadata.getSessionFactoryBuilder().build();
                logger.info("SessionFactory created");
            } catch (Exception e) {
                logger.error("Session Factory creation failed");
                logger.error(e.getMessage(), e);
                if (registry != null) {
                    StandardServiceRegistryBuilder.destroy(registry);
                }
                throw new RuntimeException(e);
            }
        }
        return sessionFactory;
    }
}
