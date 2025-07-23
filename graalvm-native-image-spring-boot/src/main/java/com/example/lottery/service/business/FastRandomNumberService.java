package com.example.lottery.service.business;

import java.util.concurrent.ThreadLocalRandom;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Service;

import com.example.lottery.service.QualityLevel;
import com.example.lottery.service.QualityOfService;
import com.example.lottery.service.RandomNumberService;

@Service
@QualityOfService(QualityLevel.FAST)
@ConditionalOnProperty(name="serviceQuality",havingValue = "fast")
public class FastRandomNumberService implements RandomNumberService {

	@Override
	public int generate(int min, int max) {
		System.err.println("FastRandomNumberService::generate");
		return ThreadLocalRandom.current().nextInt(min, max);
	}

}
