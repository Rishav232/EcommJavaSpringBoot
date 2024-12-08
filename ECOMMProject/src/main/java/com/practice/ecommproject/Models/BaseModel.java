package com.practice.ecommproject.Models;

import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@MappedSuperclass
public class BaseModel {
    @Id
    private Long Id;

    private Date createdAt;

    private Date updatedAt;

    private boolean isDeleted;
}
