package com.gong.scheduled;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class GongScheduledApplication {

    public static void main(String[] args) {
        SpringApplication.run(GongScheduledApplication.class, args);
    }

}
