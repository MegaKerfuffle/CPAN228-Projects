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
import org.springframework.web.bind.annotation.RequestParam;



@Controller
@RequestMapping("/admin")
public class AdminController {
    private final ItemService itemService;

    public AdminController(ItemService itemService) {
        this.itemService = itemService;
    }

    //#region GET Mappings
    @GetMapping(value = { "/add-item", "/update-item/{id}" }) 
    public String getForm(Model model, @PathVariable Optional<Integer> id) {
        Item item;
        // If ID is present, we're updating.
        if (id.isPresent()) {
            Optional<Item> itemOptional = itemService.getItemById(id.get());
            if (!itemOptional.isPresent())
                return "redirect:/catalog?msg=Invalid item ID given!";
            item = itemOptional.get();
        }
        // If ID not present, we're adding.
        else
            item = new Item();

        model.addAttribute("item", item);
        return "item-form";
    }

    @GetMapping("/delete/{id}")
    public String getRemoveItem(@PathVariable int id, Model model) {
        Optional<Item> toRemove = itemService.getItemById(id);
        if (!toRemove.isPresent()) {
            return "redirect:/catalog?msg=Couldn't remove item!";
        }

        itemService.deleteItem(toRemove.get());
        return "redirect:/catalog?msg=Item removed successfully!";
    }
    //#endregion



    @PostMapping("/post-item")
    public String postItem(
        @ModelAttribute Item item, 
        @RequestParam(required = false) String isUpdate
        ) {
        itemService.saveItem(item);
        
        String alert = isUpdate == null ? "Item update successfully!" : "Item added successfully!";
        return "redirect:/catalog?msg=" + alert;
    }
}
