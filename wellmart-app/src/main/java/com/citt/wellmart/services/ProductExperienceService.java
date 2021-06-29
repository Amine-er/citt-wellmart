package com.citt.wellmart.services;

import com.citt.wellmart.entities.ProductExperience;
import com.citt.wellmart.repositories.ProductExperienceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ProductExperienceService {
    @Autowired
    private ProductExperienceRepository productExperienceRepository;

    public ProductExperience saveProductExperience(ProductExperience productExperience)
    { return productExperienceRepository.save(productExperience);}

    public ProductExperience updateProductExperience(ProductExperience productExperience)
    { return productExperienceRepository.save(productExperience);}

    public List<ProductExperience> getAllProductExperience(){
        return productExperienceRepository.findAll();
    }

    public void deleteProductExperienceById(Long id){ productExperienceRepository.deleteById(id); }
}
