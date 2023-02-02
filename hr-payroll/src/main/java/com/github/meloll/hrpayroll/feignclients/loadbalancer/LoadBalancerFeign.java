package com.github.meloll.hrpayroll.feignclients.loadbalancer;

import feign.Feign;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.loadbalancer.annotation.LoadBalancerClient;
import org.springframework.context.annotation.Bean;

@LoadBalancerClient(value = "hr-worker",configuration = WorkerFeignLoadBalancerConfiguration.class)
public class LoadBalancerFeign {

    @LoadBalanced
    @Bean
    public Feign.Builder feignBuilder(){
        return Feign.builder();
    }
}
