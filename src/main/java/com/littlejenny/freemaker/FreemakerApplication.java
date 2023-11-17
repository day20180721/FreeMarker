package com.littlejenny.freemaker;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;

@SpringBootApplication
@PropertySource("classpath:config/path.properties")
public class FreemakerApplication {

    public static void main(String[] args) {
        SpringApplication.run(FreemakerApplication.class, args);
    }

}
