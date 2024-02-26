package com.example.assignment2.controllers;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.assignment2.models.Item;
import com.example.assignment2.services.ItemService;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class CatalogController {

    @Value("${business.name}")
    private String businessName;
    @Value("${page.size}")
    private int pageSize;
    
    private final ItemService service;


    /** Constructor for dependency injection of `ItemService` */
    public CatalogController(ItemService service) {
        this.service = service;
    }

    /** Redirects certain endpoints back to the catalog. */
    @GetMapping(value = {"/", "/catalog"})
    public String getCatalogRedirect() {
        return "redirect:/catalog/1";
    }

    @GetMapping("/catalog/{pageNumber}")
    public String getCatalog(
        Model model, 
        @PathVariable int pageNumber,
        @RequestParam(required=false) String alert,
        @RequestParam(required=false) String sortField,
        @RequestParam(required=false) String sortDirection,
        @RequestParam(required=false) String brand,
        @RequestParam(required=false) Integer year
        ) {


        if (brand != null && year != null) {
            model.addAttribute("items", service.getFilteredItems(brand, year));
            return getTemplate("catalog", model);
        }

        // Null check parameters
        sortField = sortField == null ? "id" : sortField;
        sortDirection = sortDirection == null ? "asc" : sortDirection;

        // Get items to display
        Page<Item> items = service.getItems(pageNumber, sortField, sortDirection, brand, pageNumber);

        // Add items
        model.addAttribute("items", items.getContent());
        model.addAttribute("item", new Item());

        // Add alert msg
        model.addAttribute("alert", alert);
        
        // Add pagination details
        model.addAttribute("totalItems", items.getTotalElements());
        model.addAttribute("totalPages", items.getTotalPages());
        model.addAttribute("currentPage", pageNumber);

        // Add current sort
        model.addAttribute("sortField", sortField);
        model.addAttribute("sortDirection", sortDirection);
        model.addAttribute("reverseSort", sortDirection.equalsIgnoreCase("asc") ? "desc" : "asc");

        // Add filters
        model.addAttribute("brand", brand);
        model.addAttribute("year", year);

        return getTemplate("catalog", model);
    }
    

    @PostMapping("/add-item")
    public String postItem(@ModelAttribute Item item) {
        service.saveItem(item);
        return "redirect:/catalog/1?alert=Added item successfully!";
    }
    

    public String getTemplate(String name, Model model) {
        model.addAttribute("businessName", businessName);
        return name;
    }
}
