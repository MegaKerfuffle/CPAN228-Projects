package com.example.week3.repositories;

import java.util.ArrayList;
import java.util.List;

import com.example.week3.models.Dish;

// Rob Brzostek, N01556942
public class DishRepository {
    private static List<Dish> dishes = new ArrayList<>();


    static {
        // Add some default dishes

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

    /**
     * Returns a list of all dishes in the repository.
     */
    public static List<Dish> getDishes() {
        return dishes;
    }

    /**
     * Attempt to add a dish to the repository.
     * @param dish - Dish to add
     * @return true if added, false otherwise
     */
    public static boolean tryAddDish(Dish dish) {
        if (dishes.contains(dish)) return false;
        dishes.add(dish);
        return true;
    }
}
