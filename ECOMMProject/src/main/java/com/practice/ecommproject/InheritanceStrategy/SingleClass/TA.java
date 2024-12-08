package com.practice.ecommproject.InheritanceStrategy.SingleClass;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity(name = "sc_ta")
@DiscriminatorValue(value = "1")
public class TA extends User{

    private float ratings;
}
