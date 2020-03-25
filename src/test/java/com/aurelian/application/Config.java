package com.aurelian.application;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Config {
    @Bean
    public CacheService getCacheService() {
        return new CacheService();
    }
}
