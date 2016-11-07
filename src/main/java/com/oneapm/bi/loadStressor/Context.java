package com.oneapm.bi.loadStressor;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Context {

    @Bean
    public Config newConfig() {
        return new Config();
    }

    @Bean
    public Counter newCounter() {
        return new Counter();
    }
}
