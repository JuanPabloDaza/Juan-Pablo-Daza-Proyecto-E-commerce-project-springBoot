package com.jtspringproject.JtSpringProject;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

import com.jtspringproject.JtSpringProject.dao.productDao;
import com.jtspringproject.JtSpringProject.models.Product;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import com.jtspringproject.JtSpringProject.services.productService;

import java.util.Arrays;
import java.util.List;

@SpringBootTest
public class ProductServiceTest {

    @InjectMocks
    productService productService;

    @Mock
    productDao productDao;

    @BeforeEach
    public void init() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void getProductsTest() {
        Product product1 = new Product();
        Product product2 = new Product();
        when(productDao.getProducts()).thenReturn(Arrays.asList(product1, product2));

        List<Product> products = productService.getProducts();

        assertEquals(2, products.size());
        verify(productDao, times(1)).getProducts();
    }

    @Test
    public void addProductTest() {
        Product product = new Product();
        when(productDao.addProduct(product)).thenReturn(product);

        Product createdProduct = productService.addProduct(product);

        assertEquals(product, createdProduct);
        verify(productDao, times(1)).addProduct(product);
    }

    @Test
    public void getProductTest() {
        Product product = new Product();
        int id = 1;
        when(productDao.getProduct(id)).thenReturn(product);

        Product fetchedProduct = productService.getProduct(id);

        assertEquals(product, fetchedProduct);
        verify(productDao, times(1)).getProduct(id);
    }

    @Test
    public void updateProductTest() {
        Product product = new Product();
        int id = 1;
        when(productDao.updateProduct(product)).thenReturn(product);

        Product updatedProduct = productService.updateProduct(id, product);

        assertEquals(product, updatedProduct);
        verify(productDao, times(1)).updateProduct(product);
    }

    @Test
    public void deleteProductTest() {
        int id = 1;
        when(productDao.deletProduct(id)).thenReturn(true);

        boolean isDeleted = productService.deleteProduct(id);

        assertEquals(true, isDeleted);
        verify(productDao, times(1)).deletProduct(id);
    }
}