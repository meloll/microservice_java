package com.github.meloll.hroauth.dtos;

import lombok.Data;

import java.io.Serializable;

@Data
public class RoleDto implements Serializable {
    private Long id;
    private String roleName;
}
