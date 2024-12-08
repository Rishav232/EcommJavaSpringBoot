package com.practice.ecommproject.InheritanceStrategy.MappedSuperClass;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity(name = "mpc_mentor")
public class Mentor extends User {

    private String feedback;
}
