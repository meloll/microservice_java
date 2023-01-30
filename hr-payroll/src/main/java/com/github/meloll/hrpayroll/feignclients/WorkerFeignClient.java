package com.github.meloll.hrpayroll.feignclients;


import com.github.meloll.hrpayroll.vo.Worker;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Component
@FeignClient(name = "hr-worker",url = "localhost:8001", path = "/worker")
public interface WorkerFeignClient {


    @GetMapping(value = "/{id}")
    ResponseEntity<Worker> findId(@PathVariable Long id);
}
