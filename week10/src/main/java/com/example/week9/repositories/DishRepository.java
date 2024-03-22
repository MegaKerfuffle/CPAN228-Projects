package com.example.week9.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.week9.models.Dish;

public interface DishRepository extends JpaRepository<Dish, Integer> {
    public Dish findDishByName(String name);
}
