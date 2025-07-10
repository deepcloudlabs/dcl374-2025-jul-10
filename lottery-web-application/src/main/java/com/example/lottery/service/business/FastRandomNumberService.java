package com.example.lottery.service.business;

import java.util.concurrent.ThreadLocalRandom;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import com.example.lottery.service.QualityLevel;
import com.example.lottery.service.QualityOfService;
import com.example.lottery.service.RandomNumberService;

@Service
@QualityOfService(QualityLevel.FAST)
public class FastRandomNumberService implements RandomNumberService {

	@Override
	public int generate(int min, int max) {
		System.err.println("FastRandomNumberService::generate");
		return ThreadLocalRandom.current().nextInt(min, max);
	}

}
