package com.indomdi.core.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Data
@Configuration
@PropertySource(ignoreResourceNotFound = true, value = "classpath:application.properties")
public class AppParamsConfig {
    @Value("${signup.keep.pending.user}")
    private Integer signupKeepPendingUser;

    @Value("${select.max.allowed.columns}")
    private Integer maxAllowedColumns;

    @Value("${forgotten.password.web.page}")
    private String forgottenRedirectWebPage;

    @Value("${forgotten.password.keep.time}")
    private Integer forgottenPasswordKeepTime;
}
