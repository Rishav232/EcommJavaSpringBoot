package com.practice.ecommproject.InheritanceStrategy.SingleClass;

import jakarta.persistence.*;

@Entity(name = "sc_user")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "UserType" ,discriminatorType = DiscriminatorType.INTEGER)
public class User {
    @Id
    private Long Id;
    private String email;
    private String name;
}
