package com.practice.ecommproject.InheritanceStrategy.JoinedClass;

import jakarta.persistence.*;

@Entity(name = "jc_user")
@Inheritance(strategy = InheritanceType.JOINED)
public class User {
    @Id
    private Long Id;
    private String email;
    private String name;
}
