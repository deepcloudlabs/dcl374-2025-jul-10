package com.example.imdb.controller;

import java.util.Collection;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.annotation.RequestScope;

import com.example.imdb.domain.Movie;
import com.example.imdb.dto.CriteriaBean;
import com.example.imdb.service.MovieService;

@RestController
@RequestMapping("/movies")
@RequestScope // @Scope("request") 
@CrossOrigin
public class ImdbRestController {
	// dependency -> contract/interface
	private final MovieService movieService;

	// constructor injection
	public ImdbRestController(MovieService movieService) {
		this.movieService = movieService;
		System.out.println("constructor injection: %s".formatted(movieService.getClass().getName()));
	}

	// http://localhost:7100/imdb/api/v1/movies?pageNo=0&pageSize=10
	@GetMapping(params= {"pageNo", "pageSize"})
	public Collection<Movie> getMoviesByPage(
			@RequestParam int pageNo,
			@RequestParam int pageSize){
		return movieService.findAllMovies()
				           .stream()
				           .skip(pageNo*pageSize)
				           .limit(pageSize)
				           .toList();
	}
	
	// http://localhost:7100/imdb/api/v1/movies
	@PostMapping
	public Collection<Movie> getMovies(@RequestBody CriteriaBean criteria){
		return movieService.findAllMoviesByCriteria(criteria);
	}
}
