package com.practice.ecommproject.Controller;

import com.practice.ecommproject.DTO.SearchRequestDto;
import com.practice.ecommproject.Models.Product;
import com.practice.ecommproject.Service.ISearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/search")
public class SearchController {

    @Autowired
    ISearchService iSearchService;

    @PostMapping
    public Page<Product> searchForAllProducts(@RequestBody SearchRequestDto searchRequestDto)
    {
        return iSearchService.searchProduct(searchRequestDto.getQuery(), searchRequestDto.getPageNumber(), searchRequestDto.getPageSize(), searchRequestDto.getSortingOrders());


    }

}
