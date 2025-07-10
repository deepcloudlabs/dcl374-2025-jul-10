package com.example.imdb.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import com.example.imdb.service.SequenceService;
import com.example.imdb.service.business.SequenceServiceImpl;

@Configuration
public class AppConfig {

	@Bean(name = "elma")
	@Scope("singleton")
	SequenceService createSequenceService() {
		System.out.println("createSequenceService() is called.");
		return new SequenceServiceImpl();
	}
}
