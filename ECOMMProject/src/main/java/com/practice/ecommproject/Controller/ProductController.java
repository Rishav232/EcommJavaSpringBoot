package com.practice.ecommproject.Controller;

import com.practice.ecommproject.DTO.CategoryDto;
import com.practice.ecommproject.DTO.ProductDTO;
import com.practice.ecommproject.Models.Category;
import com.practice.ecommproject.Models.Product;

import com.practice.ecommproject.Service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.web.servlet.HttpEncodingAutoConfiguration;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
public class ProductController {

    @Autowired
    @Qualifier("fkps")
    IProductService iProductService;
    @Autowired
    private HttpEncodingAutoConfiguration httpEncodingAutoConfiguration;

    @GetMapping("/getProducts")
    public ResponseEntity<List<ProductDTO>> getAllProducts() {

        List<Product> products = iProductService.getAllProducts();
        List<ProductDTO> response = new ArrayList<>();
        for(Product product : products)
        {
            response.add(getProductDTO(product));
        }

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    @PutMapping("/updateProduct/{id}")
    public ResponseEntity<ProductDTO> updateProduct(@PathVariable Long id,@RequestBody ProductDTO requestBody)
    {
//        System.out.println(requestBody.toString());
        Product product = getProduct(requestBody);

        Product response = iProductService.updateProduct(id,product);

        return new ResponseEntity<>(getProductDTO(response),HttpStatus.OK);
    }
    @GetMapping("/getProduct/{id}")
    public ResponseEntity<ProductDTO> getSingleProduct(@PathVariable Long id) {
        try {
            if (id <= 0) {
                throw new IllegalArgumentException("Illegal Arguments");
            }

            Product product = iProductService.getProduct(id);
            return new ResponseEntity<>(getProductDTO(product),HttpStatus.OK);
        } catch (IllegalArgumentException illegalArgumentException) {
            throw illegalArgumentException;
        }
    }
    @PostMapping("/createProduct")
    public ResponseEntity<ProductDTO> createProduct(@RequestBody ProductDTO product)
    {
        Product response = iProductService.createProduct(getProduct(product));
        ProductDTO productDTO = getProductDTO(response);

        return new ResponseEntity<>(productDTO,HttpStatus.CREATED);
    }
    public Product getProduct(ProductDTO productDTO)
    {
        Product Product = new Product();
        Product.setName(productDTO.getName());
        Product.setPrice(productDTO.getPrice());
        Product.setId(productDTO.getId());
        Product.setImageUrl(productDTO.getImageUrl());
        Product.setDescription(productDTO.getDescription());
        if(productDTO.getCategory()!=null)
        {
            Category category = new Category();
            category.setName(productDTO.getCategory().getName());
            category.setDescription(productDTO.getDescription());
            category.setId(productDTO.getCategory().getId());
            Product.setCategory(category);
        }
        return Product;
    }
    @GetMapping("/getProductByUser/{userId}/{id}")
    public ResponseEntity<ProductDTO> getProductByUser(@PathVariable Long id,@PathVariable Long userId) {
        try {
            if (id <= 0||userId<0) {
                throw new IllegalArgumentException("Illegal Arguments");
            }

            Product product = iProductService.getProductByUserRole(userId,id);
            return new ResponseEntity<>(getProductDTO(product),HttpStatus.OK);
        } catch (IllegalArgumentException illegalArgumentException) {
            throw illegalArgumentException;
        }
    }
    public ProductDTO getProductDTO(Product product) {
        ProductDTO productDTO = new ProductDTO();
        productDTO.setId(product.getId());
        productDTO.setName(product.getName());
        productDTO.setDescription(product.getDescription());
        productDTO.setImageUrl(product.getImageUrl());
        productDTO.setPrice(product.getPrice());
        if(product.getCategory()!=null)
        {
            CategoryDto category = new CategoryDto();
            category.setName(product.getCategory().getName());
            category.setDescription(product.getCategory().getDescription());
            category.setId(product.getCategory().getId());
            productDTO.setCategory(category);
        }
        return productDTO;
    }
}
