package com.practice.ecommproject.Clients;

import com.practice.ecommproject.DTO.FakeStoreDTO;
import com.practice.ecommproject.Models.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RequestCallback;
import org.springframework.web.client.ResponseExtractor;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
@Component
public class FakeStoreApiClient {

    @Autowired
    RestTemplateBuilder restTemplateBuilder;


    public FakeStoreDTO getProduct(Long id) {
        ResponseEntity<FakeStoreDTO> responseEntity = restTemplateBuilder.build().getForEntity("https://fakestoreapi.com/products/{id}",
                FakeStoreDTO.class, id);

        return responseEntity.getBody();
    }


    public FakeStoreDTO updateProduct(Long id, Product product) {
        RestTemplate restTemplate = restTemplateBuilder.build();
        ResponseEntity<FakeStoreDTO> responseEntity = putForEntity("https://fakestoreapi.com/products/{id}",product,FakeStoreDTO.class,id);


        return responseEntity.getBody();
    }


    public FakeStoreDTO[] getAllProducts() {
        RestTemplate restTemplate = restTemplateBuilder.build();
        FakeStoreDTO []fakeStoreDTOS = restTemplate.getForEntity("https://fakestoreapi.com/products",FakeStoreDTO[].class).getBody();

        return fakeStoreDTOS;
    }
    public <T> ResponseEntity<T> putForEntity(String url, @Nullable Object request, Class<T> responseType, Object... uriVariables) throws RestClientException {
        RestTemplate restTemplate = restTemplateBuilder.build();
        RequestCallback requestCallback = restTemplate.httpEntityCallback(request, responseType);
        ResponseExtractor<ResponseEntity<T>> responseExtractor = restTemplate.responseEntityExtractor(responseType);
        return restTemplate.execute(url, HttpMethod.PUT, requestCallback, responseExtractor, uriVariables);
    }
}
