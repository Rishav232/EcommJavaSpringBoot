package com.practice.ecommproject.DTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SortingOrder {

    private String sortByVariable;

    private SortingOrderType sortingOrderType;
}
