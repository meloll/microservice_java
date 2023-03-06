package com.github.meloll.hrconfigserver;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

@SpringBootApplication
@EnableConfigServer
public class HrConfigServerApplication {


    public static void main(String[] args) {
        SpringApplication.run(HrConfigServerApplication.class, args);
    }

}
