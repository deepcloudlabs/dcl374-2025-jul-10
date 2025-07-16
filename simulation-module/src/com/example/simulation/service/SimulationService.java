package com.example.simulation.service;

import java.util.List;

import com.example.random.service.RandomDoubleService;
import com.example.random.service.RandomIntegerService;

public interface SimulationService {
	void setRandomIntegerService(RandomIntegerService randomIntegerService);

	void setRandomDoubleService(RandomDoubleService randomDoubleService);

	List<Integer> generateRandomValues(int min, int max, int length, boolean sorted, boolean unique);

	List<Double> generateRandomValues(double min, double max, int length, boolean sorted, boolean unique);
}
