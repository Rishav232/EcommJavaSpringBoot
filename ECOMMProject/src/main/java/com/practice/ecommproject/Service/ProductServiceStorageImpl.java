package com.practice.ecommproject.Service;

import com.practice.ecommproject.DTO.UserDto;
import com.practice.ecommproject.InheritanceStrategy.TablesPerClass.User;
import com.practice.ecommproject.Models.Category;
import com.practice.ecommproject.Models.Product;
import com.practice.ecommproject.Repository.CategoryRepository;
import com.practice.ecommproject.Repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;

//@Primary
@Service
public class ProductServiceStorageImpl implements IProductService{

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private RestTemplate restTemplate;

    @Override
    public Product getProduct(Long id) {
        Optional<Product> productOptional = productRepository.findById(id);
        return productOptional.orElse(null);
    }

    @Override
    public Product updateProduct(Long id, Product product) {
        return null;
    }

    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public Product createProduct(Product product) {

        Category category = categoryRepository.findById(product.getCategory().getId()).orElse(null);
        if(category!=null)
        {
            product.setCategory(category);
        }
        return productRepository.save(product);
    }

    @Override
    public Product getProductByUserRole(Long userId, Long productId) {

        Product product = productRepository.findById(productId).get();

        UserDto user = restTemplate.getForEntity("http://ECommAuth/user/{userId}", UserDto.class,userId).getBody();

        if(user!=null)
            return product;

        return null;

    }
}
