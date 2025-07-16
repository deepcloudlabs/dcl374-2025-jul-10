package com.example.random.service.business;

import java.security.SecureRandom;
import java.util.Random;

import com.example.random.service.QualityLevel;
import com.example.random.service.RandomIntegerService;
import com.example.random.service.ServiceQuality;

@ServiceQuality(value=QualityLevel.SECURE)
public class SecureRandomIntegerService implements RandomIntegerService {

	private Random secureRandom = new SecureRandom();

	@Override
	public int generate(int min, int max) {
		System.out.println("SecureRandomIntegerService::generate");
		return secureRandom.nextInt(min, max) ;
	}

}
