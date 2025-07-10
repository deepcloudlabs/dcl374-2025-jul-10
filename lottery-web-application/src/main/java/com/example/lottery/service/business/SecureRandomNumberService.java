package com.example.lottery.service.business;

import java.security.SecureRandom;
import java.util.Random;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import com.example.lottery.service.RandomNumberService;

@Service
//@QualityOfService(QualityLevel.SECURE)
//@ConditionalOnProperty(name="serviceQuality",havingValue = "secure")
@Profile({
	"test", "preprod", "prod", "default"
})
public class SecureRandomNumberService implements RandomNumberService {

	private Random random= new SecureRandom();

	@Override
	public int generate(int min, int max) {
		System.err.println("SecureRandomNumberService::generate");
		return random.nextInt(min,max);
	}

}
