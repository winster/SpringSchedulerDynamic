package com.winster.spring.scheduledtask.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@Getter @Setter
@ConfigurationProperties(prefix = "scheduled")
public class ConfigParams {

    private int fixedratedelay;
}
