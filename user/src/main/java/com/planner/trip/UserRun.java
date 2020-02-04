package com.planner.trip;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class UserRun {
    public static void main(String[] args) {
        SpringApplication.run(UserRun.class, args);
    }
}
