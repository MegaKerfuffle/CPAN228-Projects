package com.example.assignment3;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.example.assignment3.models.Item;
import com.example.assignment3.services.ItemService;

@SpringBootApplication
public class Assignment3Application implements CommandLineRunner{

	private final ItemService itemService;

	/** Constructor for dependency injection */
	public Assignment3Application(ItemService service) {
		itemService = service;
	}

	public static void main(String[] args) {
		SpringApplication.run(Assignment3Application.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		itemService.saveItem(new Item(3, "Denim Jeans", "Levi's", 2023, 49.99));
		itemService.saveItem(new Item(4, "Graphic T-shirt", "Nike", 2022, 29.99));
		itemService.saveItem(new Item(5, "Hooded Sweatshirt", "Adidas", 2023, 39.99));
		itemService.saveItem(new Item(6, "Cargo Shorts", "Wrangler", 2024, 34.99));
		itemService.saveItem(new Item(7, "Plaid Shirt", "Levi's", 2022, 59.99));
		itemService.saveItem(new Item(8, "Athletic Leggings", "Adidas", 2023, 44.99));
		itemService.saveItem(new Item(9, "Windbreaker Jacket", "Nike", 2022, 79.99));
		itemService.saveItem(new Item(10, "Polo Shirt", "Ralph Lauren", 2023, 49.99));		
	}
}
