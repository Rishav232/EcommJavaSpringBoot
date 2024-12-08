package com.practice.ecommproject.InheritanceStrategy.JoinedClass;

import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;

@Entity(name = "jc_ta")
@PrimaryKeyJoinColumn(name = "user_id")
public class TA extends User {

    private float ratings;
}
