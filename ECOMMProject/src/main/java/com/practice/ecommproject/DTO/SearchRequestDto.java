package com.practice.ecommproject.DTO;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class SearchRequestDto {

    private String query;
    private Integer pageNumber;
    private Integer pageSize;

    List<SortingOrder> sortingOrders = new ArrayList<>();
}
