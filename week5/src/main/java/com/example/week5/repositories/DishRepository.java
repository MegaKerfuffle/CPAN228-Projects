package com.example.week5.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.week5.models.Dish;


@Repository
public interface DishRepository extends JpaRepository<Dish, Integer> {
    
}
