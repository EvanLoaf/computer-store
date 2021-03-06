package com.gmail.evanloafakahaitao.computer.store.config;

import com.gmail.evanloafakahaitao.computer.store.controllers.properties.WebProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.authentication.www.BasicAuthenticationEntryPoint;

@Configuration
@Order(1)
public class RestSecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.antMatcher(WebProperties.REST_API_ENTRY_POINT_PREFIX + "/**")
                .authorizeRequests().anyRequest().fullyAuthenticated()
                .and()
                    .httpBasic().authenticationEntryPoint(authenticationEntryPoint())
                .and()
                    .csrf().disable();

    }

    @Bean
    public AuthenticationEntryPoint authenticationEntryPoint() {
        BasicAuthenticationEntryPoint entryPoint = new BasicAuthenticationEntryPoint();
        entryPoint.setRealmName("REST API");
        return entryPoint;
    }
}
