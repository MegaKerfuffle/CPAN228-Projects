package com.example.week8.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.example.week8.models.Dish;
import com.example.week8.repositories.DishRepository;

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

    public List<Dish> getFilteredDishes(String category, Double price) {
        return repo.findByCategoryAndPrice(category, price);
    }

    public Page<Dish> getPaginatedDishes(int pageNum, int pageSize, String sortField, String sortDir) {
        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? 
            Sort.by(sortField).ascending() :
            Sort.by(sortField).descending();


        Pageable pageable = PageRequest.of(pageNum - 1, pageSize, sort);
        return repo.findAll(pageable);
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
