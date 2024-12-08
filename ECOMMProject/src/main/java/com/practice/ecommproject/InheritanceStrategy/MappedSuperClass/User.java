package com.practice.ecommproject.InheritanceStrategy.MappedSuperClass;

import jakarta.persistence.*;


@MappedSuperclass
public class User {
    @Id
    private Long Id;
    private String email;
    private String name;
}
