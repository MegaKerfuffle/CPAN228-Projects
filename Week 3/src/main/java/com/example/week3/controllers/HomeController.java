package com.example.week3.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.example.week3.models.Dish;
import com.example.week3.services.DishService;
import org.springframework.web.bind.annotation.PostMapping;


// Rob Brzostek, N01556942
@Controller
public class HomeController {
    @Autowired
    DishService dishService;

    @Value("${restaurant.name}")
    public String name;
 

    /**
     * Returns the home page template.
     */
    @GetMapping("/home")
    public String getHome(Model model) {
        return getTemplate("home", model);
    }


    /**
     * Returns the menu page template.
     */
    @GetMapping("/menu")
    public String getMenu(Model model) {
		model.addAttribute("dishes", dishService.getDishes());
        return getTemplate("menu", model);
    } 


    /**
     * Returns the add dishes form page, with an attribute for
     * adding new dishes to the repository.
     */
    @GetMapping("/add-dish")
    public String getAddDish(Model model) {
        model.addAttribute("item", new Dish());
        return getTemplate("add_dish", model);
    }

    
    /**
     * Handle incoming post requests for adding a dish. Returns 
     * to menu page when complete.
     */
    @PostMapping("/post-dish")
    public String postNewDish(Model model, @ModelAttribute Dish dish) {
        // Validate dish and return to menu
        validateNewDish(model, dish);
        return getMenu(model);
    }
    

    /**
     * Check if the new dish can be added, and do so if possible.
     * Adds a status attribute to the given Model, that can be
     * either 'success' or 'failure'.
     */
    private void validateNewDish(Model model, Dish dish) {
        String status = "success";
        String report = "Succesfully added dish!";

        // Fail if ID given is invalid
        if (dish.getId() <= dishService.getLastDishId()) {
            status = "failure";
            report = "Failed to add dish - ID already used!";
            model.addAttribute(status, report);
            return;
        }
        // Fail if couldn't add to repository
        if (!dishService.tryAddDish(dish)) {
            status = "failure";
            report = "Failed to add dish - internal error!";
            model.addAttribute(status, report);
            return;
        }
        
        // Add the attribute
        model.addAttribute(status, report);
    }

    /**
     * Abstracts out common logic from the get methods
     * below, to simplify the process of adding new pages.
     */
    private String getTemplate(String template, Model model) {
        model.addAttribute("restaurantName", name);
        return template;
    }
    
}
