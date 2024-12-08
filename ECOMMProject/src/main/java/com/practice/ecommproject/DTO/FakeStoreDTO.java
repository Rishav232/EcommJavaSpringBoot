package com.practice.ecommproject.DTO;

import com.practice.ecommproject.Models.Category;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class FakeStoreDTO implements Serializable {
    private Long Id;
    private String title;

    private String description;

    private Double price;

    private String category;

    private String imageUrl;
}
