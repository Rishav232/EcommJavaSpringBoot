package com.practice.ecommproject.InheritanceStrategy.TablesPerClass;

import jakarta.persistence.*;

@Entity(name = "tpc_user")
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class User {
    @Id
    private Long Id;
    private String email;
    private String name;
}
