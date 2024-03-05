package com.example.week6.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.week6.models.Dish;
import com.example.week6.services.DishService;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequestMapping("/restaurant")
public class DishMVCController {
    private final DishService service;

    @Value("${restaurant.name}")
    private String name;

    @Value("${page.size}")
    private int pageSize;

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
    public String getMenuRedirect() {
        return "redirect:/restaurant/menu/1";
    }
    
    @GetMapping("/menu/{pageNum}")
    public String getMenu(
            Model model, 
            @PathVariable int pageNum,
            @RequestParam(required=false) String msg, 
            @RequestParam(required=false) String sortField,
            @RequestParam(required=false) String sortDirection
            ) {

        // TODO: re-add filtering
        
        // Null checks for params
        sortField = sortField == null ? "id" : sortField;
        sortDirection = sortDirection == null ? "asc" : sortDirection;

        // Get our page using the params
        Page<Dish> page = service.getPaginatedDishes(pageNum, pageSize, sortField, sortDirection);
        

        // Add attributes
        model.addAttribute("dishes", page.getContent());
        model.addAttribute("msg", msg);

        // Pagination info
        model.addAttribute("totalItems", service.getAllDishes().size());
        model.addAttribute("totalPages", page.getTotalPages());
        model.addAttribute("currentPage", pageNum);

        // Sorting info
        model.addAttribute("sortField", sortField);
        model.addAttribute("sortDirection", sortDirection);
        model.addAttribute("reverseSort", sortDirection.equals("asc") ? "desc" : "asc");

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
        return "redirect:/restaurant/menu/1?msg=Dish added successfully!";
    }
    
    @PostMapping("/update-dish")
    public String postUpdateDish(@ModelAttribute Dish dish) {
        service.saveDish(dish);
        return "redirect:/restaurant/menu/1?msg=Dish updated successfully!";
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
        return "redirect:/restaurant/menu/1?msg=" + status;  
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
