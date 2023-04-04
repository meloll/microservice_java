package com.github.meloll.hroauth;

import com.github.meloll.hroauth.security.OauthProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableEurekaClient
@EnableFeignClients
@SpringBootApplication
@EnableConfigurationProperties(OauthProperties.class)
public class HrOauthApplication {

    public static void main(String[] args) {
        SpringApplication.run(HrOauthApplication.class, args);
    }

}
