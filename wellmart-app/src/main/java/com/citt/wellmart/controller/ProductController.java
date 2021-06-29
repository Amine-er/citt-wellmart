package com.citt.wellmart.controller;

import com.citt.wellmart.entities.Product;
import com.citt.wellmart.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {
    @Autowired
    private ProductService productService;

    @PostMapping
    public Product saveProduct(@RequestBody Product product)
    { return productService.saveProduct(product);}
    @PutMapping
    public Product updateProduct(@RequestBody Product product)
    { return productService.updateProduct(product);}
    @GetMapping
    public List<Product> getAllProduct(){
        return productService.getAllProduct();
    }
    @DeleteMapping(value = "/{id}")
    public void deleteProductById(@PathVariable(name = "id") Long id){ productService.deleteProductById(id); }
}
