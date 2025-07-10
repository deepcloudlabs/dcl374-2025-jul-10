package com.example.lottery.service.business;

import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.IntStream;

import org.springframework.stereotype.Service;

import com.example.lottery.dto.LotteryModel;
import com.example.lottery.service.LotteryService;
import com.example.lottery.service.RandomNumberService;

@Service
public class StandardLotteryService implements LotteryService {

	/*
	private final RandomNumberService randomNumberService;

	public StandardLotteryService(
			@QualityOfService(QualityLevel.SECURE) RandomNumberService randomNumberService) {
		this.randomNumberService = randomNumberService;
	}
    */
	
	private final List<RandomNumberService> randomNumberServices;
	private final Map<String,RandomNumberService> mapRandomNumberServices;
	private static final AtomicInteger counter = new AtomicInteger(0);
	
	public StandardLotteryService(List<RandomNumberService> randomNumberServices,Map<String,RandomNumberService> mapRandomNumberServices) {
		this.randomNumberServices = randomNumberServices;
		this.mapRandomNumberServices = mapRandomNumberServices;
	}

	@Override
	public List<LotteryModel> draw(int column) {
		return IntStream.range(0, column)
				        .mapToObj(_ -> this.draw())
				        .toList();
	}

	public LotteryModel draw() {
	    var randomNumberService = randomNumberServices.get(counter.getAndIncrement()%randomNumberServices.size());
		return new LotteryModel(
				IntStream.generate(() -> randomNumberService.generate(1,60))
		         .distinct()
		         .limit(6)
		         .sorted()
		         .boxed()
		         .toList());
	}
	
}
