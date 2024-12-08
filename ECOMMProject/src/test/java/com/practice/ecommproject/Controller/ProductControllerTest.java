package com.practice.ecommproject.Controller;

import com.practice.ecommproject.DTO.ProductDTO;
import com.practice.ecommproject.Models.Category;
import com.practice.ecommproject.Models.Product;
import com.practice.ecommproject.Service.IProductService;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
class ProductControllerTest {

    @Autowired
    ProductController productController;

    @MockBean
    IProductService iProductService;

    @Captor
    private ArgumentCaptor<Long> idCaptor;

    @Test
    public void Test_GetSingleProductSuccess()
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

        when(iProductService.getProduct(any(Long.class ))).thenReturn(product);

        ResponseEntity<ProductDTO> productDTOResponseEntity = productController.getSingleProduct(1L);

        assertNotNull(productDTOResponseEntity);
        assertEquals(1L,productDTOResponseEntity.getBody().getId());
        assertEquals("Samsung",productDTOResponseEntity.getBody().getName());

        verify(iProductService).getProduct(idCaptor.capture());

        assertEquals(idCaptor.getValue(),id);
    }
    @Test
    public void Test_GetSingleProduct_InvalidArgsException()
    {
        Exception exception = assertThrows(IllegalArgumentException.class,()->productController.getSingleProduct(-1L));

        assertEquals("Illegal Arguments",exception.getMessage());
        verify(iProductService,times(0)).getProduct(-1L);
    }
    @Test
    public  void Test_CreateProductSuccess()
    {
        ProductDTO productDTO = new ProductDTO();
        productDTO.setId(1L);
        productDTO.setName("Samsung");
        productDTO.setPrice(15000.0);

        Product product = new Product();
        product.setId(1L);
        product.setName("Samsung");
        product.setPrice(15000D);

        when(iProductService.createProduct(any(Product.class))).thenReturn(product);

        ProductDTO productDTO1 = productController.createProduct(productDTO).getBody();

        assertNotNull(productDTO1);
        assertEquals("Samsung",productDTO1.getName());
    }
}