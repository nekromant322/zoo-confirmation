package com.nekromant.confirmation;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;

@SpringBootApplication
@PropertySource({
        "classpath:config/application-dev.yml"
})
public class ZooConfirmationApplication {
    public static void main(String[] args) {
        SpringApplication.run(ZooConfirmationApplication.class, args);
    }
}
