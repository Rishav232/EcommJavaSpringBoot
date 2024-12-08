package com.practice.ecommproject.Service;

import com.practice.ecommproject.Models.Category;
import com.practice.ecommproject.Models.Product;
import com.practice.ecommproject.Repository.CategoryRepository;
import com.practice.ecommproject.Repository.ProductRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest
class ProductServiceStorageImplTest {

    @Autowired
    private IProductService iProductService;

    @MockBean
    private ProductRepository productRepository;

    @MockBean
    private CategoryRepository categoryRepository;

    @Test
    @Transactional
    public void getAllProductSuccess()
    {
        Product product1 = new Product();
        product1.setId(1L);
        product1.setName("Samsung");
        product1.setPrice(15000.0);

        Product product = new Product();
        product.setId(1L);
        product.setName("Samsung");
        product.setPrice(15000D);

        List<Product> products = new ArrayList<>();
        products.add(product1);
        products.add(product);

        when(productRepository.findAll()).thenReturn(products);

        List<Product> productList = iProductService.getAllProducts();

        assertTrue(!productList.isEmpty());
    }
    @Test
    @Transactional
    public void createProductSuccess()
    {
        Long id=1L;
        Product product = new Product();
        product.setId(id);
        product.setName("Samsung");
        product.setPrice(15000.0);
        product.setDescription("Best Phone in Town");
        Category category = new Category();
        category.setId(2L);
        category.setDescription("Electronics");
        category.setName("Mobile Device");
        product.setCategory(category);

        when(categoryRepository.findById(2L)).thenReturn(Optional.of(category));

        when(productRepository.save(product)).thenReturn(product);

        Product response = iProductService.createProduct(product);

        assertNotNull(response);
        assertEquals(response.getName(),product.getName());
        System.out.println(response.getCategory().getName());
    }
}