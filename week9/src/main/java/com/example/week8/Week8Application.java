package com.example.week8;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.example.week8.models.Dish;
import com.example.week8.services.DishService;

@SpringBootApplication
public class Week8Application implements CommandLineRunner {

	private final DishService service;

	// dep injection
	public Week8Application(DishService service) {
		this.service = service;
	}

	public static void main(String[] args) {
		SpringApplication.run(Week8Application.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		// Add dishes with the command line runner
		service.saveDish(new Dish(1, "Pizza", "Non-veg", 12));
		service.saveDish(new Dish(2, "Chicken Sandwich", "Non-veg", 18));
		service.saveDish(new Dish(3, "Shawarma", "Vegan", 10));
		service.saveDish(new Dish(6, "Biryani", "Non-veg", 14));
		service.saveDish(new Dish(7, "Fries", "Veg", 4));
		service.saveDish(new Dish(8, "Poutine", "Non-veg", 14));
		service.saveDish(new Dish(9, "Hotdog", "Non-veg", 14));
		service.saveDish(new Dish(8, "Rice", "Veg", 3));
	}
}