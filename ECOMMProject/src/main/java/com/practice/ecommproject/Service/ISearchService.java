package com.practice.ecommproject.Service;

import com.practice.ecommproject.DTO.SortingOrder;
import com.practice.ecommproject.Models.Product;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ISearchService {

    Page<Product> searchProduct(String query, int pageNumber, int pageSize, List<SortingOrder> sortingOrders);
}
