package com.practice.ecommproject.Controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jayway.jsonpath.JsonPath;
import com.practice.ecommproject.DTO.ProductDTO;
import com.practice.ecommproject.Models.Product;
import com.practice.ecommproject.Service.IProductService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(ProductController.class)
public class ProductControllerTestMVC {

    @Autowired
    private  MockMvc mockMvc;

    @MockBean
    IProductService iProductService;

    @Autowired
    ObjectMapper objectMapper;

    @Test
    public void getAllProductsSuccess() throws Exception {
        mockMvc.perform(get("/api/getProducts")).andExpect(status().isOk());
    }
    @Test
    public void getAllProducts_StatusAndContent() throws Exception {
        Product product1 = new Product();
        product1.setId(1L);
        product1.setName("Samsung");
        product1.setPrice(15000.0);

        Product product = new Product();
        product.setId(1L);
        product.setName("IPhone");
        product.setPrice(15000D);

        List<Product> products = new ArrayList<>();
        products.add(product1);
        products.add(product);

        when(iProductService.getAllProducts()).thenReturn(products);

        mockMvc.perform(get("/api/getProducts"))
                .andExpect(status().isOk())
                .andExpect(content().string(objectMapper.writeValueAsString(products)))
                .andExpect(jsonPath("$[1].name").value("IPhone"));
    }
    @Test
    public void createProduct_ContentSuccess() throws Exception {
        Product product = new Product();
        product.setId(1L);
        product.setName("Samsung");
        product.setPrice(15000D);

        ProductDTO productDTO = new ProductDTO();
        productDTO.setId(1L);
        productDTO.setName("Samsung");
        productDTO.setPrice(15000.0);
        when(iProductService.createProduct(any(Product.class))).thenReturn(product);

        mockMvc.perform(post("/api/createProduct")
                .content(objectMapper.writeValueAsString(productDTO))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(content().string(objectMapper.writeValueAsString(productDTO)))
                .andExpect(jsonPath("$.name").value("Samsung"));

    }
}
