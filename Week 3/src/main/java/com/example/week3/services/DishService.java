package com.example.week3.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.week3.models.Dish;
import com.example.week3.repositories.DishRepository;

@Service
public class DishService {

    public List<Dish> getDishes() {
        return DishRepository.dishes;
    }
}
