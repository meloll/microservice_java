package com.github.meloll.hrpayroll.feignclients.loadbalancer.loadbalanceralternative;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.DefaultServiceInstance;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.loadbalancer.core.ServiceInstanceListSupplier;
import reactor.core.publisher.Flux;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class WorkerInstaceSupplier implements ServiceInstanceListSupplier {

    @Value("${hr-worker.name}")
    private String serviceId;

    private static final List<String> HOSTS = List.of("localhost");
    private static final List<Integer> PORTS = Arrays.asList(8002,8003);


    @Override
    public String getServiceId() {
        return this.serviceId;
    }
    @Override
    public Flux<List<ServiceInstance>> get() {
        return Flux.just(getListServiceIstance());
    }


    private List<ServiceInstance> getListServiceIstance(){

        List<ServiceInstance> instanceList = new ArrayList<>();
        int id = 1;

        for(String host:WorkerInstaceSupplier.HOSTS){
            for(Integer port: WorkerInstaceSupplier.PORTS){
                instanceList.add(
                        new DefaultServiceInstance(serviceId + id, serviceId,host,port,false)
                );
                id++;
            }
        }

        return instanceList;
    }

    /**
    @Override
    public Flux<List<ServiceInstance>> get() {
        return Flux.just(Arrays.asList(
                new DefaultServiceInstance(serviceId + "1",serviceId,"localhost",8002,false),
                new DefaultServiceInstance(serviceId + "2",serviceId,"localhost",8003,false)
        ));
    }
     */

}
