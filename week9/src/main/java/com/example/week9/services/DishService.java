package com.example.week9.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.week9.models.Dish;
import com.example.week9.repositories.DishRepository;

@Service
public class DishService {

    private final DishRepository dishRepo;

    public DishService(DishRepository dishRepo) {
        this.dishRepo = dishRepo;
    }

    /** Returns all dishes */
    public List<Dish> getAllDishes() {
        return dishRepo.findAll();
    }

    /** Returns a single dish with the matching ID */
    public Dish getDishById(int id) {
        return dishRepo.findById(id).orElse(null);
    }

    /** Adds a dish */
    public void addDish(Dish dish) {
        Dish existing = dishRepo.findDishByName(dish.getName());
        if (existing != null) {
            throw new IllegalStateException("Dish already exists!");
        }

        dishRepo.save(dish);
    }

    /** Updates an existing dish */
    public void updateDish(int dishId, Dish dish) {
        boolean dishExists = dishRepo.existsById(dishId);
        if (!dishExists) {
            throw new IllegalStateException("Can't update dish - doesn't exist!");
        }

        // Set dish ID, to ensure we're *updating* and not *adding*
        dish.setId(dishId);
        dishRepo.save(dish);
    }

    /** Deletes an existing dish */
    public void deleteDish(int dishId) {
        boolean dishExists = dishRepo.existsById(dishId);
        if (!dishExists) {
            throw new IllegalStateException("Can't delete dish - doesn't exist!");
        }

        dishRepo.deleteById(dishId);
    }

}
