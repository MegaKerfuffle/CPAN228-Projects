package com.example.week9.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.week9.models.Category;

public interface CategoryRepository extends JpaRepository<Category, Integer> {
    public Category findByName(String name);
}
