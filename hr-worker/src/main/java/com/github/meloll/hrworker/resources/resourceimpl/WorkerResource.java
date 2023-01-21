package com.github.meloll.hrworker.resources.resourceimpl;

import com.github.meloll.hrworker.entities.Worker;
import com.github.meloll.hrworker.repositories.WorkerRepository;
import com.github.meloll.hrworker.resources.resourceinterface.ResourceInteface;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RequestMapping(value = "/worker")
@AllArgsConstructor
@RestController
public class WorkerResource implements ResourceInteface<Worker> {

    WorkerRepository repository;

    public ResponseEntity<List<Worker>> findAll(){

        List<Worker> list = repository.findAll();

        return ResponseEntity.ok(list);

    }

    public ResponseEntity<Worker> findId(Long id){

        Optional<Worker> worker = repository.findById(id);

        return worker.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());

    }



}
