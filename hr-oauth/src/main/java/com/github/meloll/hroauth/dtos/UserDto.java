package com.github.meloll.hroauth.dtos;

import lombok.Data;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Data
public class UserDto implements Serializable {

    private Long id;
    private String name;
    private String email;
    private String password;

    private Set<RoleDto> roles = new HashSet<>();
}
