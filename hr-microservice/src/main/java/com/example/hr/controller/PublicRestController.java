package com.example.hr.controller;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.IntStream;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.annotation.RequestScope;

@RestController
@RequestScope
@RequestMapping("/public")
@CrossOrigin
@Validated
public class PublicRestController {

	@GetMapping
	public List<Integer> getRandomNumbers(){
		return IntStream.generate(() ->ThreadLocalRandom.current().nextInt(1, 60)).distinct().limit(6).sorted().boxed().toList();
	}
}
