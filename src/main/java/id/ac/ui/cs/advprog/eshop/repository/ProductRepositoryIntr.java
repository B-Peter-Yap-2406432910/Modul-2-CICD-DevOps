package id.ac.ui.cs.advprog.eshop.repository;

import java.util.Iterator;
import id.ac.ui.cs.advprog.eshop.model.Product;

public interface ProductRepositoryIntr {
    Product create(Product product);
    Iterator<Product> findAll();
    Product findProduct(String id);
    Product delete(Product product);
    Product edit(Product product);
}