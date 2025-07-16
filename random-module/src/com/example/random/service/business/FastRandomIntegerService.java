package com.example.random.service.business;

import java.util.concurrent.ThreadLocalRandom;

import com.example.random.service.QualityLevel;
import com.example.random.service.RandomIntegerService;
import com.example.random.service.ServiceQuality;

@ServiceQuality(value=QualityLevel.FAST)
public class FastRandomIntegerService implements RandomIntegerService {

	@Override
	public int generate(int min, int max) {
		System.out.println("FastRandomIntegerService::generate");
		return ThreadLocalRandom.current().nextInt(min, max);
	}

}
