package com.example.random.service.business;

import java.security.SecureRandom;
import java.util.Random;

import com.example.random.service.QualityLevel;
import com.example.random.service.RandomDoubleService;
import com.example.random.service.ServiceQuality;

@ServiceQuality(value = QualityLevel.SECURE)
public class SecureRandomDoubleService implements RandomDoubleService {

	private Random secureRandom = new SecureRandom();

	@Override
	public double generate(double min, double max) {
		System.out.println("SecureRandomDoubleService::generate");
		return secureRandom.nextDouble(min, max);
	}

}
