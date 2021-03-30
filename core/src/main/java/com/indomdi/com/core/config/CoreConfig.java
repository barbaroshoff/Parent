package com.indomdi.com.core.config;


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
        "com.indomdi.com.core.service",
        "com.indomdi.com.core.exception",
        "com.indomdi.com.core.converter" })
@EnableScheduling
@EnableCaching
@EnableJpaAuditing
@EnableJpaRepositories({ "com.indomdi.com.core.dao" })
@EntityScan({ "com.indomdi.com.core.persistent" })
public class CoreConfig {

}
