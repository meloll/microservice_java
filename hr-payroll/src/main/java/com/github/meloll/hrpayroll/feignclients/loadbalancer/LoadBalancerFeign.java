package com.github.meloll.hrpayroll.feignclients.loadbalancer;

import feign.Feign;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;


/**
 * @Deprecated disabling loadbalancer to use Eureka's loadbalancer
 */
//@LoadBalancerClient(value = "hr-worker",configuration = WorkerFeignLoadBalancerConfiguration.class)
@Deprecated
public class LoadBalancerFeign {

    //@LoadBalanced
    //@Bean
    public Feign.Builder feignBuilder(){
        return Feign.builder();
    }
}
