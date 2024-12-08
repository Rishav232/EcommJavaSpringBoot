package com.practice.ecommproject.stubs;

import com.practice.ecommproject.InheritanceStrategy.TablesPerClass.User;
import com.practice.ecommproject.Models.Product;
import com.practice.ecommproject.Service.IProductService;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ProductServiceStub implements IProductService {

    Map<Long, Product> mp;

    public ProductServiceStub() {
        this.mp = new HashMap<>();
    }

    @Override
    public Product getProduct(Long id) {
        return mp.get(id);
    }

    @Override
    public Product updateProduct(Long id, Product product) {
        mp.put(id,product);
        return mp.get(id);
    }

    @Override
    public List<Product> getAllProducts() {
        return List.of();
    }

    @Override
    public Product createProduct(Product product) {
        mp.put(product.getId(),product);

        return mp.get(product.getId());
    }

    @Override
    public Product getProductByUserRole(Long userId, Long productId) {
        return null;
    }


}
