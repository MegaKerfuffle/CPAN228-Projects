package com.example.assignment3;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.example.assignment3.models.Item;
import com.example.assignment3.services.ItemService;

@SpringBootApplication
public class Assignment3Application implements CommandLineRunner{

	private final ItemService itemService;

	/** Dependency injection of item service */
	public Assignment3Application(ItemService service) {
		itemService = service;
	}

	public static void main(String[] args) {
		SpringApplication.run(Assignment3Application.class, args);
	}

	/** Handles the running of command-line commands. */
	@Override
	public void run(String... args) throws Exception {
		// Add a bunch of default items
		itemService.saveItem(new Item(1, "Denim Jeans", "Levi's", 2023, 49.99));
		itemService.saveItem(new Item(2, "Graphic T-shirt", "Nike", 2022, 29.99));
		itemService.saveItem(new Item(3, "Hooded Sweatshirt", "Adidas", 2023, 39.99));
		itemService.saveItem(new Item(4, "Cargo Shorts", "Wrangler", 2024, 34.99));
		itemService.saveItem(new Item(5, "Plaid Shirt", "Levi's", 2022, 59.99));
		itemService.saveItem(new Item(6, "Athletic Leggings", "Adidas", 2023, 44.99));
		itemService.saveItem(new Item(7, "Windbreaker Jacket", "Nike", 2022, 79.99));
		itemService.saveItem(new Item(8, "Polo Shirt", "Ralph Lauren", 2023, 49.99));		
	}
}
