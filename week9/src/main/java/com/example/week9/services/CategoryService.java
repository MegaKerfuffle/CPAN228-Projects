package com.example.week9.services;

import org.springframework.stereotype.Service;

import com.example.week9.models.Category;
import com.example.week9.repositories.CategoryRepository;

@Service
public class CategoryService {

    private final CategoryRepository categoryRepo;

    /** Injects dependency: Category Repository */
    public CategoryService(CategoryRepository categoryRepo) {
        this.categoryRepo = categoryRepo;
    }

    /** Retrieves a category by its name */
    public Category getCategoryByName(String name) {
        return categoryRepo.findByName(name);
    }
}
