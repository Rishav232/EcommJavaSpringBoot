package com.practice.ecommproject.Service;

import com.practice.ecommproject.InheritanceStrategy.TablesPerClass.User;
import com.practice.ecommproject.Models.Product;
import org.springframework.stereotype.Service;

import java.util.List;


public interface IProductService {

    Product getProduct(Long id);
    Product updateProduct(Long id,Product product);
    List<Product> getAllProducts();
    Product createProduct(Product product);
    Product getProductByUserRole(Long userId,Long productId);
}
