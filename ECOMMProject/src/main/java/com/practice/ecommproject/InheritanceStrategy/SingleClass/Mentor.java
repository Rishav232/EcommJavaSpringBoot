package com.practice.ecommproject.InheritanceStrategy.SingleClass;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity(name = "sc_mentor")
@DiscriminatorValue(value = "2")
public class Mentor extends User{

    private String feedback;
}
