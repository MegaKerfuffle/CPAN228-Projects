package com.example.week7.controllers;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.ui.Model;

import com.example.week7.services.DishService;

/** 
 * Base class for creating view controllers, with common elements
 * abstracted out to avoid repeating yourself (DRY) when writing new
 * controllers in the future.
 */
public abstract class ViewControllerBase {
    
    @Value("${restaurant.name}")
    protected String restaurantName;

    @Value("${page.size}")
    protected int pageSize;
    

    protected final DishService dishService;

    /** CTOR for dependency injection of `DishService` */
    protected ViewControllerBase(DishService dishService) {
        this.dishService = dishService;
    }

    
    /**
     * Returns a Thymeleaf template by name, while adding specific
     * common attributes.
     * @param name - name of the template
     * @param model - model to add attributes to
     * @return Name of template
     */
    public String getTemplate(String name, Model model) {
        // This could be expanded in future with more attributes,
        // or maybe validation for the given name.
        model.addAttribute("restaurantName", restaurantName);
        return name;
    }
}
