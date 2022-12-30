package com.example.simpleAPI;

import com.example.simpleAPI.model.Student;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;

@EnableJpaRepositories
@SpringBootApplication(exclude = SecurityAutoConfiguration.class)
public class SimpleApiApplication {
    public static void main(String[] args) {
        SpringApplication.run(SimpleApiApplication.class, args);
    }
}


