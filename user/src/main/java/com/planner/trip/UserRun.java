package com.planner.trip;

import com.planner.trip.config.security.JwtManager;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
public class UserRun {
    public static void main(String[] args) {
        SpringApplication.run(UserRun.class, args);
    }
}
