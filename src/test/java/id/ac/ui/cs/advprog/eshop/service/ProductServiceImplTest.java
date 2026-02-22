package id.ac.ui.cs.advprog.eshop.service;

import id.ac.ui.cs.advprog.eshop.model.Product;
import id.ac.ui.cs.advprog.eshop.repository.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ProductServiceImplTest {

    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private ProductServiceImpl productService;

    private Product product;

    @BeforeEach
    void setUp() {
        product = new Product();
        product.setProductId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        product.setProductName("Sampo Cap Bambang");
        product.setProductQuantity(100);
    }

    @Test
    void testCreate() {
        when(productRepository.create(product)).thenReturn(product);
        Product savedProduct = productService.create(product);
        assertEquals(product.getProductId(), savedProduct.getProductId());
        verify(productRepository, times(1)).create(product);
    }

    @Test
    void testFindAll() {
        List<Product> productList = new ArrayList<>();
        productList.add(product);
        Iterator<Product> iterator = productList.iterator();
        when(productRepository.findAll()).thenReturn(iterator);

        List<Product> allProducts = productService.findAll();
        assertFalse(allProducts.isEmpty());
        assertEquals(1, allProducts.size());
        verify(productRepository, times(1)).findAll();
    }

    @Test
    void testFindProduct() {
        when(productRepository.findProduct(product.getProductId())).thenReturn(product);
        Product foundProduct = productService.findProduct(product.getProductId());
        assertEquals(product.getProductId(), foundProduct.getProductId());
        verify(productRepository, times(1)).findProduct(product.getProductId());
    }

    @Test
    void testDelete() {
        when(productRepository.findProduct(product.getProductId())).thenReturn(product);
        when(productRepository.delete(product)).thenReturn(product);

        Product deletedProduct = productService.delete(product.getProductId());
        assertEquals(product.getProductId(), deletedProduct.getProductId());
        verify(productRepository, times(1)).delete(product);
    }

    @Test
    void testDeleteIfNull() {
        when(productRepository.findProduct("invalid-id")).thenReturn(null);

        Product deletedProduct = productService.delete("invalid-id");
        assertNull(deletedProduct);
        verify(productRepository, times(0)).delete(any(Product.class));
    }

    @Test
    void testEdit() {
        when(productRepository.edit(product)).thenReturn(product);
        Product editedProduct = productService.edit(product);
        assertEquals(product.getProductId(), editedProduct.getProductId());
        verify(productRepository, times(1)).edit(product);
    }
}