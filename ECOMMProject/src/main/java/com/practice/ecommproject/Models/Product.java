package com.practice.ecommproject.Models;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@Entity
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Product extends BaseModel{

    @Id
    private Long Id;

    private String name;

    private String description;

    private Double price;

    @JsonManagedReference
    @ManyToOne(cascade = CascadeType.ALL)
    private Category category;

    private  String imageUrl;

}
