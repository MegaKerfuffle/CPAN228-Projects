package com.example.week7.controllers;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.week7.models.Dish;
import com.example.week7.services.DishService;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
@RequestMapping("/restaurant")
public class DishMVCController extends RestaurantController {


    protected DishMVCController(DishService dishService) {
        super(dishService);
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
        Page<Dish> page = dishService.getPaginatedDishes(pageNum, pageSize, sortField, sortDirection);
        

        // Add attributes
        model.addAttribute("dishes", page.getContent());
        model.addAttribute("msg", msg);

        // Pagination info
        model.addAttribute("totalItems", dishService.getAllDishes().size());
        model.addAttribute("totalPages", page.getTotalPages());
        model.addAttribute("currentPage", pageNum);

        // Sorting info
        model.addAttribute("sortField", sortField);
        model.addAttribute("sortDirection", sortDirection);
        model.addAttribute("reverseSort", sortDirection.equals("asc") ? "desc" : "asc");

        return getTemplate("menu", model);
    }
}
