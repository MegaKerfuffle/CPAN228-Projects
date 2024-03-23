package com.example.album;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class Week10AlbumApplication {

	public static void main(String[] args) {
		SpringApplication.run(Week10AlbumApplication.class, args);
	}


	// Define a `RestTemplate` bean that can be obtained later
	// via dependency injection.
	@Bean
	RestTemplate restTemplate() {
		return new RestTemplate();
	}

}
