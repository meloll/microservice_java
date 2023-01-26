package com.github.meloll.hrpayroll.services;

import com.github.meloll.hrpayroll.vo.Worker;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@Service
public class CommunicatorWorker {

    @Value("${hr-worker.host}")
    private  String pathApi;

    private RestTemplate restTemplate;

    public CommunicatorWorker(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    private String getPathWorkerForId(Long id){
        return pathApi + "/" + id;
    }


    public Worker getWorkerForId(Long id){
        return restTemplate.getForObject(getPathWorkerForId(id),Worker.class, Map.of("id",id));
    }



}
