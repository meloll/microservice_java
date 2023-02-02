package com.github.meloll.hrpayroll.feignclients.loadbalancer.loadbalanceralternative;

import org.springframework.cloud.loadbalancer.core.ServiceInstanceListSupplier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class WorkerServerInstanceConfiguration {

    @Bean
    ServiceInstanceListSupplier serviceInstanceListSupplier(){
        return new WorkerInstaceSupplier();
    }
}
