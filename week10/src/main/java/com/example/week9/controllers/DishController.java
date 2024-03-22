package com.example.week9.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.week9.models.Category;
import com.example.week9.models.Dish;
import com.example.week9.services.CategoryService;
import com.example.week9.services.DishService;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/restaurant/api/v1/")
public class DishController {

    private final DishService dishService;
    private final CategoryService categoryService;


    /** Injects dependencies: Dish Service, Category Service */
    public DishController(DishService dishService, CategoryService categoryService) {
        this.dishService = dishService;        
        this.categoryService = categoryService;
    }

    /** Get all dishes */
    @GetMapping("/dishes")
    public ResponseEntity<List<Dish>> getDishes() {
        // note: should probably be try-catching this
        return ResponseEntity.ok(dishService.getAllDishes());
    }

    /** Get a dish by its ID */
    @GetMapping("/dishes/{id}")
    public ResponseEntity<Dish> getDishById(@PathVariable Integer id) {
        // note: should probably be try-catching this
        return ResponseEntity.ok(dishService.getDishById(id));
    }
    
    /** Attempt to add a new dish */
    @PostMapping("/dishes/add")
    public ResponseEntity<String> postAddDish(@RequestBody Dish dish) {
        // Ensure the dish's category isn't duplicated on add
        Category existing = categoryService.getCategoryByName(dish.getCategory().getName());
        if (existing != null) {
            dish.setCategory(existing);
        }

        try {
            dishService.addDish(dish);
        }
        catch (Exception ex) {
            return ResponseEntity.badRequest().body(ex.getMessage());
        }

        return ResponseEntity.ok("Dish added successfully!");
    }

    /** Deletes an existing dish */
    @DeleteMapping("dishes/delete/{id}")
    public ResponseEntity<String> deleteDish(@PathVariable Integer id) {
        try {
            dishService.deleteDish(id);
        }
        catch (Exception ex) {
            return ResponseEntity.badRequest().body(ex.getMessage());
        }

        return ResponseEntity.ok("Dish deleted successfully!");
    }

    /** Updates an existing dish */
    @PutMapping("dishes/update/{id}")
    public ResponseEntity<String> putUpdateDish(@PathVariable Integer id, @RequestBody Dish dish) {
        // Ensure the dish's category isn't duplicated on update
        Category existing = categoryService.getCategoryByName(dish.getCategory().getName());
        if (existing != null) {
            dish.setCategory(existing);
        }

        try {
            dishService.updateDish(id, dish);
        }
        catch (Exception ex) {
            return ResponseEntity.badRequest().body(ex.getMessage());
        }
        return ResponseEntity.ok("Dish updated successfully!");
    }


}
