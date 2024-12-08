package com.practice.ecommproject.DTO;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.practice.ecommproject.Models.Category;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ProductDTO {

    private  Long Id;

    private String name;

    private String description;

    private Double price;

    private CategoryDto category;

    private  String imageUrl;
}
