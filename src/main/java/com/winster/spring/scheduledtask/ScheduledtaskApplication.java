package com.winster.spring.scheduledtask;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableConfigurationProperties
public class ScheduledtaskApplication {

    public static void main(String[] args) {
        SpringApplication.run(ScheduledtaskApplication.class, args);
    }

}
