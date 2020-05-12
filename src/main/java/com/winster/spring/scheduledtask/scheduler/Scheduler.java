package com.winster.spring.scheduledtask.scheduler;

import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;

@Configuration
@EnableScheduling
public class Scheduler {

    @Bean
    @RefreshScope
    public MyJob processJob(){
        return new MyJob();
    }

}
