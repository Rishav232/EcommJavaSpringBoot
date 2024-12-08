package com.practice.ecommauth.DTO;

import com.practice.ecommauth.Models.Role;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
public class UserDto {

    private String email;

    private Set<Role> roles;
}
