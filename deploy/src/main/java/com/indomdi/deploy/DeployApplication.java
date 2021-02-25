package com.indomdi.deploy;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages = {
        "com.indomdi.deploy",
        "com.indomdi.web",
        "com.indomdi.core"
})
@PropertySource("classpath:application.properties")
@EnableJpaRepositories("com.indomdi.core.dao")
@EntityScan("com.indomdi.core.persistent")
public class DeployApplication  extends ServletInitializer {

    public static void main(String[] args) {
        SpringApplication.run(DeployApplication.class, args);
    }

}
