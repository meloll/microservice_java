package com.github.meloll.hroauth.resources;

import com.github.meloll.hroauth.dtos.UserDto;
import com.github.meloll.hroauth.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/users")
public class UserResources {

    @Autowired
    private UserService service;

    @GetMapping(value = "/search")
    public ResponseEntity<UserDto> findByEmail(@RequestParam String email){
        UserDto user = service.findByEmail(email);
        return ResponseEntity.ok(user);

    }


}
