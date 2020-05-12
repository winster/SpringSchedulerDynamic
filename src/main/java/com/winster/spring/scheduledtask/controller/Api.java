package com.winster.spring.scheduledtask.controller;

import com.winster.spring.scheduledtask.config.ConfigParams;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class Api {

    @Autowired
    private ConfigParams configParams;

    @GetMapping("/test")
    public String resource() {
        return "test:"+configParams.getFixedratedelay();
    }
}
