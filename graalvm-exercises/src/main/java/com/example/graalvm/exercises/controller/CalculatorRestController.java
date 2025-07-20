package com.example.graalvm.exercises.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.graalvm.exercises.dto.request.CalculatorRequest;
import com.example.graalvm.exercises.service.CalculatorService;

@RestController
@RequestMapping("/calculator")
public class CalculatorRestController {
	private final CalculatorService calculatorService;
	
	
	public CalculatorRestController(CalculatorService calculatorService) {
		this.calculatorService = calculatorService;
	}


	@PostMapping
	public String evaluate(@RequestBody CalculatorRequest request) {
		return calculatorService.run(request.function(), request.args()).toString();
	}
}
