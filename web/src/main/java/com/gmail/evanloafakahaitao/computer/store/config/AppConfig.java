package com.gmail.evanloafakahaitao.computer.store.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;

@Configuration
@PropertySource({
        "classpath:database.properties",
        "classpath:web.properties",
        "classpath:xml.properties"
})
@ComponentScan(basePackages = {
        "com.gmail.evanloafakahaitao.computer.store.config",
        "com.gmail.evanloafakahaitao.computer.store.dao",
        "com.gmail.evanloafakahaitao.computer.store.services",
        "com.gmail.evanloafakahaitao.computer.store.controllers"
})
public class AppConfig {

    @Bean
    public static PropertySourcesPlaceholderConfigurer configurer() {
        return new PropertySourcesPlaceholderConfigurer();
    }
}
