package com.practice.ecommproject.InheritanceStrategy.MappedSuperClass;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity(name = "mpc_ta")
public class TA extends User {

    private float ratings;
}
