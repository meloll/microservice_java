package com.github.meloll.hrworker.resources.resourceimpl;

import com.github.meloll.hrworker.entities.Worker;
import com.github.meloll.hrworker.repositories.WorkerRepository;
import com.github.meloll.hrworker.resources.resourceinterface.ResourceInteface;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;
@RefreshScope
@RequestMapping(value = "/worker")
@RestController
@AllArgsConstructor
public class WorkerResource implements ResourceInteface<Worker> {

    WorkerRepository repository;
    Environment  env;
    private static final Logger logger = LoggerFactory.getLogger(WorkerResource.class);

    private static Integer callNumber = 0;

    @Value("${test.config}")
    private String config;
    public ResponseEntity<List<Worker>> findAll(){

        List<Worker> list = repository.findAll();

        return ResponseEntity.ok(list);

    }


    @GetMapping(value = "/configs")
    public String getConfig(){

        logger.info("CONFIG = "+config);
        return config;
    }

    public ResponseEntity<Worker> findId(Long id) throws InterruptedException {

        //Thread.sleep(5000); test timeout resilience4j
        logger.info("PORT: "+ env.getProperty("local.server.port"));
        logger.info("Call Number: " + callNumber);
        Optional<Worker> worker = repository.findById(id);
        callNumber+= 1;

        return worker.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());

    }



}
