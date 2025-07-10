package com.example.imdb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
// Component -> Bean -> Spring Bean
@SpringBootApplication
public class ImdbWebApplication {

	public static void main(String[] args) {
		SpringApplication.run(ImdbWebApplication.class, args);
	}

}
