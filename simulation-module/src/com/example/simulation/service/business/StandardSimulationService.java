package com.example.simulation.service.business;

import java.util.List;
import java.util.stream.DoubleStream;
import java.util.stream.IntStream;

import com.example.random.service.RandomDoubleService;
import com.example.random.service.RandomIntegerService;
import com.example.simulation.service.SimulationService;

public class StandardSimulationService implements SimulationService {
	private RandomIntegerService randomIntegerService;
	private RandomDoubleService randomDoubleService;

	@Override
	public void setRandomIntegerService(RandomIntegerService randomIntegerService) {
		this.randomIntegerService = randomIntegerService;
	}

	@Override
	public void setRandomDoubleService(RandomDoubleService randomDoubleService) {
		this.randomDoubleService = randomDoubleService;
	}

	@Override
	public List<Integer> generateRandomValues(int min, int max, int length, boolean sorted, boolean unique) {
		var numbers = IntStream.generate(() -> randomIntegerService.generate(min, max));
		if (unique)
			numbers = numbers.distinct();
		numbers = numbers.limit(length);
		if (sorted)
			numbers = numbers.sorted();
		return numbers.boxed().toList();
	}

	@Override
	public List<Double> generateRandomValues(double min, double max, int length, boolean sorted, boolean unique) {
		var numbers = DoubleStream.generate(() -> randomDoubleService.generate(min, max));
		if (unique)
			numbers = numbers.distinct();
		numbers = numbers.limit(length);
		if (sorted)
			numbers = numbers.sorted();
		return numbers.boxed().toList();
	}

}
