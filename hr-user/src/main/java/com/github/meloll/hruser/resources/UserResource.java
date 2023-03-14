package com.github.meloll.hruser.resources;

import com.github.meloll.hruser.entities.User;
import com.github.meloll.hruser.repositories.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RequestMapping(value = "/users")
@RestController
@AllArgsConstructor
public class UserResource {

    UserRepository repository;

    @GetMapping(value = "/{id}")
    public ResponseEntity<User> findId(@PathVariable Long id) {
        Optional<User> user = repository.findById(id);
        return user.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());

    }

    @GetMapping(value = "/search")
    public ResponseEntity<User> findSearch(@RequestParam String email) {
        Optional<User> user = repository.findByEmail(email);
        return user.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());

    }



}

