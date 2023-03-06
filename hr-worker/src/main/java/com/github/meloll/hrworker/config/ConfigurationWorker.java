package com.github.meloll.hrworker.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ConfigurationWorker {

    @Value("${test.config}")
    private String config;


    @Bean(value = "getConfig")
    public String configProfile(){
        return config;
    }

}
