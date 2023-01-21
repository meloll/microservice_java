package com.github.meloll.hrworker.resources.resourceinterface;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

public interface ResourceInteface<T> {

    @GetMapping(value = "/{id}")
    @ResponseBody
    ResponseEntity<T> findId(@PathVariable Long id);

    @GetMapping
    @ResponseBody
    ResponseEntity<List<T>> findAll();


}
