package com.example.random.service.business;

import java.util.concurrent.ThreadLocalRandom;

import com.example.random.service.QualityLevel;
import com.example.random.service.RandomDoubleService;
import com.example.random.service.ServiceQuality;

@ServiceQuality(value=QualityLevel.FAST)
public class FastRandomDoubleService implements RandomDoubleService {

	@Override
	public double generate(double min, double max) {
		System.out.println("FastRandomDoubleService::generate");
		return ThreadLocalRandom.current().nextDouble(min, max);
	}

}
