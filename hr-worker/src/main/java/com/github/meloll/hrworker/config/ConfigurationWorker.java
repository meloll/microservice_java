package com.github.meloll.hrworker.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
@RefreshScope
@Configuration
public class ConfigurationWorker {


    @Value("${spring.profiles.active}")
    private String profile;


    @Bean(value = "getProfileConfig")
    public String getConfigProfile(){
        return profile;
    }

}
