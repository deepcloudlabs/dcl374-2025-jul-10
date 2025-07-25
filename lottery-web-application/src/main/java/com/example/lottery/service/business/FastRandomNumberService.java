package com.example.lottery.service.business;

import java.util.concurrent.ThreadLocalRandom;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import com.example.lottery.service.RandomNumberService;

@Service
//@QualityOfService(QualityLevel.FAST)
//@ConditionalOnProperty(name="serviceQuality",havingValue = "fast")
@Profile({"dev"})
public class FastRandomNumberService implements RandomNumberService {

	@Override
	public int generate(int min, int max) {
		System.err.println("FastRandomNumberService::generate");
		return ThreadLocalRandom.current().nextInt(min, max);
	}

}
