package com.example.week4.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.week4.models.Dish;
import com.example.week4.repositories.DishRepository;

@Service
public class DishService {
    
    @Autowired
    private DishRepository repo;

    public int saveDish(Dish dish) {
        return repo.save(dish);
    }

    public List<Dish> getAllDishes() {
        return repo.findAll();
    }
}
