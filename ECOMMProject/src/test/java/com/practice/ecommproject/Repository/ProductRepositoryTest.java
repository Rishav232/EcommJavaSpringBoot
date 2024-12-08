package com.practice.ecommproject.Repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ProductRepositoryTest {

    @Autowired
    ProductRepository productRepository;

    @Test
    @Transactional
    public void TestCategoryNameFromProductId()
    {
        String val = productRepository.findCategoryFromProductId(1L);
        System.out.println(val);
    }

}