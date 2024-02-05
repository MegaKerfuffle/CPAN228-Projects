package com.example.week4.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.example.week4.models.Dish;
import com.example.week4.services.DishService;
import org.springframework.web.bind.annotation.PostMapping;



@Controller
@RequestMapping("/restaurant")
public class DishMVCController {

    @Autowired
    private DishService service;

    @Value("${restaurant.name}")
    private String name;

    @GetMapping(value = { "/", "/home" })
    public String getHome(Model model) {
        return getTemplate("home", model);
    }

    @GetMapping("/menu")
    public String getMenu(Model model) {
        model.addAttribute("dishes", service.getAllDishes());
        return getTemplate("menu", model);
    }
    
    @GetMapping("/add-dish")
    public String getDishForm(Model model) {
        model.addAttribute("item", new Dish());
        return getTemplate("add-dish", model);
    }

    @PostMapping("/post-dish")
    public String postMethodName(@ModelAttribute Dish dish) {
        service.saveDish(dish);
        return "redirect:/restaurant/menu";
    }
    


    private String getTemplate(String templateName, Model model) {
        model.addAttribute("restaurantName", name);
        return templateName;
    }
}
