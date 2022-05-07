package com.gmail.evanloafakahaitao.computer.store.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = {
        "com.gmail.evanloafakahaitao.computer.store.config",
        "com.gmail.evanloafakahaitao.computer.store.dao",
        "com.gmail.evanloafakahaitao.computer.store.services",
        "com.gmail.evanloafakahaitao.computer.store.controllers"
})
public class AppConfig {
}
