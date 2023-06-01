package com.web.flux.mongo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.StopWatch;

/**
 * @author mohammad alsharif
 */
@Configuration
public class BeanConfig {

    @Bean
    public StopWatch stopWatch() {
        return new StopWatch();
    }
}
