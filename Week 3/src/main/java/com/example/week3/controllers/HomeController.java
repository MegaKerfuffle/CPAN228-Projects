package com.example.week3.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.week3.models.Dish;
import com.example.week3.services.DishService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;



@Controller
@RequestMapping("/")
public class HomeController {
    @Autowired
    DishService dishService;

    @Value("${restaurant.name}")
    public String name;
 
    @GetMapping("/home")
    public String getHome(Model model) {
        model.addAttribute("restaurantName", name);
        return "home";
    } 

    @GetMapping("/menu")
    public String getMemu(Model model) {
        model.addAttribute("restaurantName", name);
		model.addAttribute("dishes", dishService.getDishes());
        return "menu";
    }

    @GetMapping("/add_dish")
    public String getMethodName(Model model) {
        model.addAttribute("restaurantName", name);
        model.addAttribute("item", new Dish());
        return "add_dish";
    }
    

    @PostMapping("/post-")
    public String postMethodName(Model model, @ModelAttribute Dish dish) {
        model.addAttribute("dish", dish);
        return "menu";
    }
    
}
