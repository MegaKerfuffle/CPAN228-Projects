package com.example.week7.controllers;

import java.util.Optional;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.week7.models.Dish;
import com.example.week7.services.DishService;

@Controller
@RequestMapping("/admin")
public class AdminController extends ViewControllerBase {

    /** CTOR for dependency injection of `DishService` */
    protected AdminController(DishService dishService) {
        super(dishService);
    }

    // Below endpoints now require a logged in admin user


    @GetMapping("/add-dish")
    public String getDishForm(Model model) {
        model.addAttribute("item", new Dish());
        return getTemplate("add-dish", model);
    }

    @PostMapping("/add-dish")
    public String postAddDish(@ModelAttribute Dish dish) {
        // Save the dish, redirect to menu w/ success message
        dishService.saveDish(dish);
        return "redirect:/restaurant/menu/1?msg=Dish added successfully!";
    }
    
    @PostMapping("/update-dish")
    public String postUpdateDish(@ModelAttribute Dish dish) {
        dishService.saveDish(dish);
        return "redirect:/restaurant/menu/1?msg=Dish updated successfully!";
    }

    @GetMapping("/delete/{id}")
    public String removeDish(Model model, @PathVariable int id) {
        // Call delete method in service, using the ID picked
        // up from the path of the request
        int deleteStatus = dishService.deleteDish(id);
        String status = deleteStatus == 1 ? 
            "Dish removed successfully!" :
            "Dish does not exist!";

        model.addAttribute("msg", status);
        return "redirect:/restaurant/menu/1?msg=" + status;  
    }

    @GetMapping("/update/{id}")
    public String updateDish(Model model, @PathVariable int id) {
        Optional<Dish> dish = dishService.getDishById(id);
        if (!dish.isPresent()) 
            return "redirect:/restaurant/menu";
        
        model.addAttribute("item", dish);
        return getTemplate("add-dish", model);
    }
}
