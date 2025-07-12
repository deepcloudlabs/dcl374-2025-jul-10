package com.example.hr.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.example.hr.application.HrApplication;
import com.example.hr.application.business.StandardHrApplication;
import com.example.hr.infra.EventPublisher;
import com.example.hr.repository.EmployeeRepository;

@Configuration
public class AppConfig {

	@Bean                            // SPI: driven ports -> adapter
	HrApplication createHrApplication(EmployeeRepository employeeRepository, EventPublisher eventPublisher) {
		System.err.println(employeeRepository.getClass().getName());
		System.err.println(eventPublisher.getClass().getName());
		return new StandardHrApplication(employeeRepository, eventPublisher);
	}
}
