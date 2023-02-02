package com.github.meloll.hrpayroll.feignclients.loadbalancer;

import com.github.meloll.hrpayroll.feignclients.loadbalancer.loadbalanceralternative.WorkerServerInstanceConfiguration;
import feign.Feign;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.loadbalancer.annotation.LoadBalancerClient;
import org.springframework.context.annotation.Bean;

/**
 * Main LoadBalancer is active = false | Class WorkerFeignLoadBalancerConfiguration.class
 * **
 * Alternative LoadBalancer is active = true | Class WorkerServerInstanceConfiguration.class
 */
@LoadBalancerClient(value = "hr-worker",configuration = WorkerServerInstanceConfiguration.class)
public class WorkerLoadBalancerFeign {

    /**
     * change value when using another configuration
     */
    private static final Boolean  isMainLoadBalancerConfiguration = false;
    private final Logger logger = LoggerFactory.getLogger(WorkerLoadBalancerFeign.class);

    @LoadBalanced
    @Bean
    public Feign.Builder feignBuilder(){

        logger.info("[LOADBALANCER] Main Load Balancer Configuration is active:" + isMainLoadBalancerConfiguration);
        return Feign.builder();
    }
}
