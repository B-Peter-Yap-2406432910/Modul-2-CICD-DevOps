package id.ac.ui.cs.advprog.eshop.service;

import id.ac.ui.cs.advprog.eshop.model.Product;
import id.ac.ui.cs.advprog.eshop.repository.ProductRepository;
import id.ac.ui.cs.advprog.eshop.repository.ProductRepositoryIntr;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

@Service
public class ProductServiceImpl implements ProductService{

    private final ProductRepositoryIntr productRepository;

    @Autowired
    public ProductServiceImpl(ProductRepositoryIntr productRepository){
        this.productRepository = productRepository;
    }

    @Override
    public Product create(Product product){
        if (product.getProductId() == null){
            String productid = UUID.randomUUID().toString();
            product.setProductId(productid);
        }
        productRepository.create(product);
        return product;
    }

    @Override
    public List<Product> findAll(){
        Iterator<Product> productIterator = productRepository.findAll();
        List<Product> allProduct = new ArrayList<>();
        productIterator.forEachRemaining(allProduct::add);
        return allProduct;
    }

    @Override
    public Product findProduct(String productID){
        return productRepository.findProduct(productID);
    }

    @Override
    public Product delete(String productID){
        Product product = productRepository.findProduct(productID);
        if (product == null){
            return null;
        }
        productRepository.delete(product);
        return product;
    }

    @Override
    public Product edit(Product product){
        return productRepository.edit(product);
    }
}
