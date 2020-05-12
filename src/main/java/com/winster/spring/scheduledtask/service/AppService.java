package com.winster.spring.scheduledtask.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;

@Service
@Slf4j
public class AppService {

    @Async
    public void job1() {
        log.info("job1: {} {}", Thread.currentThread().getName(), LocalDateTime.now());
        aMonoMethodJob1(Thread.currentThread().getName()).subscribe(
                result -> {
                    log.info("job1: {}", result);
                },
                exception -> {
                    log.error("job1: {}", exception.getMessage());
                }
        );
    }

    public Mono<String> aMonoMethodJob1(String threadName){
        try {
            Thread.sleep(10000L);
            return Mono.just("something "+threadName+" "+ LocalDateTime.now());
        } catch (InterruptedException e) {
            log.error("AppService: aMonoMethodJob1: Interrupted!!!!");
            return Mono.error(e);
        }
    }

    public Mono<String> aMonoMethodJob2(String threadName){
        try {
            Thread.sleep(10000L);
            return Mono.just("something "+threadName+" "+ LocalDateTime.now());
        } catch (InterruptedException e) {
            log.error("AppService: aMonoMethodJob2: Interrupted!!!!");
            return Mono.error(e);
        }
    }

}
