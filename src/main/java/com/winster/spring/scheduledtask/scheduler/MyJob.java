package com.winster.spring.scheduledtask.scheduler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.Scheduled;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;

@Slf4j
@EnableAsync
public class MyJob implements RefreshScheduler {

    @Async
    @Scheduled(fixedRateString = "${scheduled.fixedratedelay}")
    public void processData() {
        log.info("insider processAnother {} {}", Thread.currentThread().getName(), LocalDateTime.now());
        aMonoMethod(Thread.currentThread().getName()).subscribe(result -> {
           log.info("received a response {}", result);
        }, error -> {
            log.error("received an error {}", error.getMessage());
        });
    }

    private Mono<String> aMonoMethod(String threadName){
        try {
            Thread.sleep(10000L);
            return Mono.just("something "+threadName+" "+LocalDateTime.now());
        } catch (InterruptedException e) {
            log.error("Interrupted!!!!");
            return Mono.error(e);
        }
    }

}
