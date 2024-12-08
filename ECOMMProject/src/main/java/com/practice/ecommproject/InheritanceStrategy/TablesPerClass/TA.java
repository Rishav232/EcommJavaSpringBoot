package com.practice.ecommproject.InheritanceStrategy.TablesPerClass;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity(name = "tpc_ta")
public class TA extends User {

    private float ratings;
}
