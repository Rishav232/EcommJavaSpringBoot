package com.practice.ecommproject.InheritanceStrategy.TablesPerClass;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity(name = "tpc_mentor")
public class Mentor extends User {

    private String feedback;
}
