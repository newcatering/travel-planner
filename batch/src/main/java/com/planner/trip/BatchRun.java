package com.planner.trip;

import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@EnableBatchProcessing
@SpringBootApplication
public class BatchRun {
    public static void main(String[] args) {
        SpringApplication.run(BatchRun.class, args);
    }
}
