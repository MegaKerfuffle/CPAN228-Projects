package com.example.week5.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.week5.models.Dish;
import com.example.week5.repositories.DishRepository;

@Service
public class DishService {
    
    @Autowired
    private DishRepository repo;

    public void saveDish(Dish dish) {
        repo.save(dish);
    }

    public List<Dish> getAllDishes() {
        return repo.findAll();
    }

    public int deleteDish(int id) {
        if (repo.existsById(id)) {
            repo.deleteById(id);
            return 1;
        }
        return 0;
    }

    public Optional<Dish> getDishById(int id) {
        return repo.findById(id);
    }
}
