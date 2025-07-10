package com.example.imdb;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
// Component -> Bean -> Spring Bean
@SpringBootApplication
public class ImdbWebApplication implements ApplicationRunner {
    private final ConfigurableApplicationContext applicationContext;
    
    
	public ImdbWebApplication(ConfigurableApplicationContext applicationContext) {
		this.applicationContext = applicationContext;
	}


	public static void main(String[] args) {
		SpringApplication.run(ImdbWebApplication.class, args);
	}


	@Override
	public void run(ApplicationArguments args) throws Exception {
		applicationContext.getBeansOfType(Object.class,false,true)
		                  .forEach((componentName,component)->{
		                	    System.out.println("%48s: %s".formatted(componentName,component.getClass().getName()));
		                  });
	}

}
