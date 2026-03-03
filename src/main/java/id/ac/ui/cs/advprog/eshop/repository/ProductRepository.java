package id.ac.ui.cs.advprog.eshop.repository;

import id.ac.ui.cs.advprog.eshop.model.Product;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class ProductRepository implements ProductRepositoryIntr{
    private List<Product> productData = new ArrayList<>();
    private HashMap<String,Product> productHashMap = new HashMap<>();

    public Product create(Product product){
        productData.add(product);
        productHashMap.put(product.getProductId(), product);
        return product;
    }

    public Iterator<Product> findAll(){
        return productData.iterator();
    }

    public Product findProduct(String productID){
        return productHashMap.get(productID);
    }

    public Product delete(Product product){
        productData.remove(product);
        productHashMap.remove(product.getProductId());
        return product;
    }

    public Product edit(Product product){
        String idProduct = product.getProductId();
        Product oldProduct = productHashMap.get(idProduct);
        if (oldProduct == null) {
            return null;
        }
        String newProductName = product.getProductName();
        int newProductQuantity = product.getProductQuantity();
        oldProduct.setProductName(newProductName);
        oldProduct.setProductQuantity(newProductQuantity);
        return oldProduct;
    }
}
