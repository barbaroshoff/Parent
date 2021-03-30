package com.indomdi.com.deploy;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication(scanBasePackages = {
        "com.indomdi.com.deploy",
        "com.indomdi.com.web",
        "com.indomdi.com.core"
})
public class DeployApplication  extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication.run(DeployApplication.class, args);
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(DeployApplication.class);
    }
}
