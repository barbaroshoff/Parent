package com.indomdi.deploy.config;


import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;

@Configuration
@PropertySources({
        @PropertySource(name = "Environment predefined configuration",
                value = "classpath:environment.properties", ignoreResourceNotFound = true),
})
@EnableAutoConfiguration
public class ExternalConfiguration {

}
