package com.example.week7;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.example.week7.models.Dish;
import com.example.week7.services.DishService;

@SpringBootApplication
public class Week7Application implements CommandLineRunner {

	private final DishService service;

	// dep injection
	public Week7Application(DishService service) {
		this.service = service;
	}

	public static void main(String[] args) {
		SpringApplication.run(Week7Application.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		// Add more dishes with the command line runner
		service.saveDish(new Dish(6, "Biryani", "Non-veg", 14));
		service.saveDish(new Dish(7, "Fries", "Veg", 4));
		service.saveDish(new Dish(8, "Poutine", "Non-veg", 14));
		service.saveDish(new Dish(9, "Hotdog", "Non-veg", 14));
		service.saveDish(new Dish(8, "Rice", "Veg", 3));
	}
}