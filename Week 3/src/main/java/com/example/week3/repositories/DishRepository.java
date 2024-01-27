package com.example.week3.repositories;

import java.util.ArrayList;
import java.util.List;

import com.example.week3.models.Dish;

public class DishRepository {
    public static List<Dish> dishes = new ArrayList<>();

    static {
        // omg builder pattern!
        dishes.add(
            Dish.builder()
            .id(1)
            .name("Chicken Biryani")
            .category("Non-veg")
            .price(12.00)
            .build()            
        );

        dishes.add(
            Dish.builder()
            .id(2)
            .name("Pizza")
            .category("Veg")
            .price(10.00)
            .build()            
        );

        dishes.add(
            Dish.builder()
            .id(3)
            .name("Shawarma")
            .category("Non-veg")
            .price(16.00)
            .build()            
        );
    }
}
