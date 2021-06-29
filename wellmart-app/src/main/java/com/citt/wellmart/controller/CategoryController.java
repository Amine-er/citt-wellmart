package com.citt.wellmart.controller;

import com.citt.wellmart.entities.Category;
import com.citt.wellmart.repositories.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/categories")
public class CategoryController {
    @Autowired
    private CategoryRepository categoryRepository;

    @PostMapping
    public Category saveCategory(@RequestBody Category city){ return categoryRepository.save(city);}

    @GetMapping
    public List<Category> getAllCategory(){
        return categoryRepository.findAll();
    }

    @DeleteMapping("/{id}")
    public void deleteCategoryById(@PathVariable(name = "id") Long id){ categoryRepository.deleteById(id); }
}
