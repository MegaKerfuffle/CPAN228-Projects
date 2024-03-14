package com.example.assignment3.controllers;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.assignment3.models.BusinessUser;
import com.example.assignment3.models.Item;
import com.example.assignment3.repositories.UserRepository;
import com.example.assignment3.services.ItemService;

@Controller
public class CatalogController {

    @Value("${business.name}")
    private String businessName;
    @Value("${page.size}")
    private int pageSize;
    
    private final ItemService service;
    private final UserRepository userRepo;


    /** Constructor for dependency injection of `ItemService` */
    public CatalogController(ItemService service, UserRepository userRepo) {
        this.service = service;
        this.userRepo = userRepo;
    }

    /** Redirects certain endpoints back to the catalog. */
    @GetMapping(value = {"/", "/catalog"})
    public String getCatalogRedirect() {
        return "redirect:/catalog/1";
    }

    /** Retrieves the catalog page */
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
        
        // Always-added attributes
        model.addAttribute("businessName", businessName);

        // Add alert msg
        model.addAttribute("alert", alert);

        // Get currently logged-in user and pass some details as attributes.
        // This was annoying and completely unnecessary, but kinda cool.
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        UserDetails userDetails = (UserDetails)auth.getPrincipal();
        Optional<BusinessUser> userOptional = userRepo.findByUsername(userDetails.getUsername());
        if (userOptional.isPresent()) {
            BusinessUser user = userOptional.get();

            // Add user attributes
            model.addAttribute("username", user.getUsername());
            model.addAttribute("role", user.getRole());
        }


        // If filtered, skip the pagination
        if (brand != null && year != null) {
            model.addAttribute("items", service.getFilteredItems(brand, year));
            return "catalog";
        }

        // Null check parameters
        sortField = sortField == null ? "id" : sortField;
        sortDirection = sortDirection == null ? "asc" : sortDirection;

        // Get items to display
        Page<Item> items = service.getItems(pageNumber, sortField, sortDirection, brand, pageNumber);

        // Add items
        model.addAttribute("items", items.getContent());
        model.addAttribute("item", new Item());

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


        return "catalog";
    }
}