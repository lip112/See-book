package com.example.seebook;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class SeeBookApplication {

    public static void main(String[] args) {
        SpringApplication.run(SeeBookApplication.class, args);

    }

}
