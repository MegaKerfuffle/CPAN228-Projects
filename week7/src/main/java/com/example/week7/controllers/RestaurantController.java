package com.example.week7.controllers;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.ui.Model;

import com.example.week7.services.DishService;

public abstract class RestaurantController {
    
    @Value("${restaurant.name}")
    protected String restaurantName;

    @Value("${page.size}")
    protected int pageSize;

    
    protected final DishService dishService;



    protected RestaurantController(DishService dishService) {
        this.dishService = dishService;
    }


    public String getTemplate(String name, Model model) {
        model.addAttribute("restaurantName", restaurantName);
        return name;
    }
}
