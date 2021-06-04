package com.citt.wellmart.services;

import com.citt.wellmart.entities.Category;
import com.citt.wellmart.repositories.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;

    public Category saveCategory(Category city){ return categoryRepository.save(city);}

    public List<Category> getAllCategory(){
        return categoryRepository.findAll();
    }

    public void deleteCategoryById(Long id){ categoryRepository.deleteById(id); }
}
