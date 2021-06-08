package com.citt.wellmart.controller;

import com.citt.wellmart.entities.ProductExperience;
import com.citt.wellmart.services.ProductExperienceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/productsExperience")
public class ProductExperienceController {
    @Autowired
    private ProductExperienceService productExperienceService;

    @PostMapping
    public ProductExperience saveProductExperience(@RequestBody ProductExperience productExperience)
    { return productExperienceService.saveProductExperience(productExperience);}
    @PutMapping
    public ProductExperience updateProductExperience(@RequestBody ProductExperience productExperience)
    { return productExperienceService.updateProductExperience(productExperience);}
    @GetMapping
    public List<ProductExperience> getAllProductExperience(){
        return productExperienceService.getAllProductExperience();
    }
    @DeleteMapping(value = "/{id}")
    public void deleteProductExperienceById(@PathVariable(name = "id") Long id){ productExperienceService.deleteProductExperienceById(id); }
}
