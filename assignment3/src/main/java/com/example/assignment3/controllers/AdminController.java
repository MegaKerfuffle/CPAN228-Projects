package com.example.assignment3.controllers;

import java.util.Optional;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import com.example.assignment3.models.Item;
import com.example.assignment3.services.ItemService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/admin")
public class AdminController {
    private final ItemService itemService;

    public AdminController(ItemService itemService) {
        this.itemService = itemService;
    }

    //#region ADMIN GET MAPPINGS
    @GetMapping("/item-form")
    public String getForm(Model model) {
        
        return "catalog";
    }
    

    //#endregion



    // TODO: add path var or something that indicates add vs. update of item
    @PostMapping(value = { "/post-item", "/post-item/{isUpdate}" })
    public String postItem(
        @ModelAttribute Item item, 
        @PathVariable boolean isUpdate
        ) {
        itemService.saveItem(item);
        
        String alert = isUpdate ? "Item update successfully!" : "Item added successfully!";
        return "redirect:/catalog?msg=" + alert;
    }

    @GetMapping("/delete/{id}")
    public String getRemoveDish(@PathVariable int id, Model model) {
        Optional<Item> toRemove = itemService.getItemById(id);
        if (!toRemove.isPresent()) {

        }



        // TODO: add alert message
        return "redirect:/catalog";
    }
    
    
}
