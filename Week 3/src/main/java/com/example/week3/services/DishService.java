package com.example.week3.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.week3.models.Dish;
import com.example.week3.repositories.DishRepository;

// Rob Brzostek, N01556942
@Service
public class DishService {

    /**
     * Returns a list of all added dishes
     */
    public List<Dish> getDishes() {
        return DishRepository.getDishes();
    }

    /**
     * Attempt to add a dish to the Dish Repository.
     * @param dish - Dish to be added
     * @return true if added, false otherwise
     */
    public boolean tryAddDish(Dish dish) {
        return DishRepository.tryAddDish(dish);
    }

    /**
     * Retrieves the ID of the last dish in the Dish Repository.
     */
    public int getLastDishId() 
    {
        List<Dish> dishes = getDishes();
        Dish last = dishes.get(dishes.size() - 1);
        return last.getId();
    }
}
