package com.indomdi.core.config;


import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;

@Configuration
@ComponentScan({
        "com.indomdi.core.service",
        "com.indomdi.core.exception",
        "com.indomdi.core.converter" })
@EnableScheduling
@EnableCaching
@EnableJpaAuditing
@EnableJpaRepositories(basePackages = { "com.indomdi.core.dao" })
@EntityScan(basePackages = { "com.indomdi.core.persistent" })
@PropertySource("classpath:application.properties")
public class CoreConfig {
}
