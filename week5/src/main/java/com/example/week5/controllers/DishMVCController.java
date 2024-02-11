package com.example.week5.controllers;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.week5.models.Dish;
import com.example.week5.services.DishService;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;




@Controller
@RequestMapping("/restaurant")
public class DishMVCController {
    private final DishService service;

    @Value("${restaurant.name}")
    private String name;

    // Constructor injection for the `DishService`
    // (better than field injection for immutability)
    public DishMVCController(DishService service) {
        this.service = service;
    }


    @GetMapping(value = { "/", "/home" })
    public String getHome(Model model) {
        return getTemplate("home", model);
    }

    @GetMapping("/menu")
    public String getMenu(Model model, @RequestParam(required=false) String msg) {
        model.addAttribute("dishes", service.getAllDishes());
        model.addAttribute("msg", msg);
        return getTemplate("menu", model);
    }
    
    @GetMapping("/add-dish")
    public String getDishForm(Model model) {
        model.addAttribute("item", new Dish());
        return getTemplate("add-dish", model);
    }

    @PostMapping("/add-dish")
    public String postAddDish(@ModelAttribute Dish dish) {
        // Add a dish
        service.saveDish(dish);
        return "redirect:/restaurant/menu?msg=Dish added successfully!";
    }
    
    @PostMapping("/update-dish")
    public String postUpdateDish(@ModelAttribute Dish dish) {
        service.saveDish(dish);
        return "redirect:/restaurant/menu?msg=Dish updated successfully!";
    }

    @GetMapping("/delete/{id}")
    public String removeDish(Model model, @PathVariable int id) {
        // Call delete method in service, using the ID picked
        // up from the path of the request
        int deleteStatus = service.deleteDish(id);
        String status = deleteStatus == 1 ? 
            "Dish removed successfully!" :
            "Dish does not exist!";

        model.addAttribute("msg", status);
        return "redirect:/restaurant/menu?msg=" + status;  
    }


    @GetMapping("/update/{id}")
    public String updateDish(Model model, @PathVariable int id) {
        Optional<Dish> dish = service.getDishById(id);
        if (!dish.isPresent()) 
            return "redirect:/restaurant/menu";
        
        model.addAttribute("item", dish);
        return getTemplate("add-dish", model);
    }
    
    

    private String getTemplate(String templateName, Model model) {
        model.addAttribute("restaurantName", name);
        return templateName;
    }
}
