package com.indomdi.web.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;


@Configuration
@ComponentScan(basePackages = {"com.indomdi.web",
                                "com.indomdi.core"})
public class ExtConf {
}
