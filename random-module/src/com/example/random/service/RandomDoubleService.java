package com.example.random.service;

public interface RandomDoubleService {
	double generate(double min, double max);

	default double generate(double max) {
		return generate(1.0, max);
	}

}
