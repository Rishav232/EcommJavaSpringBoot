package com.practice.ecommproject.DTO;

import com.practice.ecommproject.Models.Role;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
public class UserDto {

    private String email;

    private Set<Role> roles;
}
