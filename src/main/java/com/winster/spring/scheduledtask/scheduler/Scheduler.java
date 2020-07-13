package com.winster.spring.scheduledtask.scheduler;

import com.winster.spring.scheduledtask.service.AppService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.cloud.context.scope.refresh.RefreshScopeRefreshedEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

@Configuration
@EnableScheduling
@Slf4j
@RefreshScope
public class Scheduler implements ApplicationListener<RefreshScopeRefreshedEvent> {

    @Autowired
    private AppService appService;

    @Scheduled(fixedRateString = "${scheduled.job1Interval}")
    public void job1() {
        appService.job1();
    }

    @Override
    public void onApplicationEvent(RefreshScopeRefreshedEvent refreshScopeRefreshedEvent) {

    }
}
