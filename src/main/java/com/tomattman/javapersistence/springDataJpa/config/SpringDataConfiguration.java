package com.tomattman.javapersistence.springDataJpa.config;

import org.hibernate.boot.model.naming.PhysicalNamingStrategy;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringDataConfiguration {

    @Bean
    public PhysicalNamingStrategy physicalNamingStrategy() {
        return new CENamingStrategy();
    }
}
