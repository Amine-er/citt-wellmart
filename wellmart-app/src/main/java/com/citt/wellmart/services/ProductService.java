package com.citt.wellmart.services;

import com.citt.wellmart.entities.Product;
import com.citt.wellmart.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;

    public Product saveProduct(Product product)
    { return productRepository.save(product);}

    public Product updateProduct(Product product)
    { return productRepository.save(product);}

    public List<Product> getAllProduct(){
        return productRepository.findAll();
    }

    public void deleteProductById(Long id){ productRepository.deleteById(id); }
}
