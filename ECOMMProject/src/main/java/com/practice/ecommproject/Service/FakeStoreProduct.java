package com.practice.ecommproject.Service;

import com.practice.ecommproject.Clients.FakeStoreApiClient;
import com.practice.ecommproject.DTO.FakeStoreDTO;
import com.practice.ecommproject.InheritanceStrategy.TablesPerClass.User;
import com.practice.ecommproject.Models.Category;
import com.practice.ecommproject.Models.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service("fkps")
public class FakeStoreProduct implements IProductService{


    @Autowired
    FakeStoreApiClient fakeStoreApiClient;

    @Autowired
    RedisTemplate<String,Object> redisTemplate;

    @Override
    public Product getProduct(Long id) {
        FakeStoreDTO responseEntity = (FakeStoreDTO) redisTemplate.opsForHash().get("PRODUCT__",id);

        if(responseEntity!=null)
        {
            System.out.println("FOUND IN CACHE");
            return getProduct(responseEntity);
        }
        responseEntity = fakeStoreApiClient.getProduct(id);

        if(responseEntity!=null)
        {
            System.out.println("Not found in Cache , so inserting in Cache ");
            redisTemplate.opsForHash().put("PRODUCT__",id,responseEntity);
            return getProduct(responseEntity);
        }

        return null;
    }

    @Override
    public Product updateProduct(Long id, Product product) {
        FakeStoreDTO responseEntity = fakeStoreApiClient.updateProduct(id,product);
        if(responseEntity!=null)
        {
            return getProduct(responseEntity);
        }
        return null;
    }

    @Override
    public List<Product> getAllProducts() {

        FakeStoreDTO []fakeStoreDTOS = fakeStoreApiClient.getAllProducts();

        List<Product> productList = new ArrayList<>();
        for(FakeStoreDTO fakeStoreDTO : fakeStoreDTOS)
        {
            productList.add(getProduct(fakeStoreDTO));
        }
        return productList;
    }

    @Override
    public Product createProduct(Product product) {
        return null;
    }

    @Override
    public Product getProductByUserRole(Long userId, Long productId) {
        return null;
    }


    public Product getProduct(FakeStoreDTO fakeStoreDTO)
    {
        Product product = new Product();
        product.setId(fakeStoreDTO.getId());
        product.setName(fakeStoreDTO.getTitle());
        product.setDescription(fakeStoreDTO.getDescription());
        product.setImageUrl(fakeStoreDTO.getImageUrl());
        product.setPrice(fakeStoreDTO.getPrice());
        if(fakeStoreDTO.getCategory()!=null) {
            Category category = new Category();
            category.setName(fakeStoreDTO.getCategory());
            product.setCategory(category);
        }
        return product;
    }
}
