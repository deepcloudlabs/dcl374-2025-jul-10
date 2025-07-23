package com.example.lottery.service.business;

import java.util.List;
import java.util.stream.IntStream;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.example.lottery.dto.LotteryModel;
import com.example.lottery.service.LotteryService;
import com.example.lottery.service.RandomNumberService;

@Service
public class StandardLotteryService implements LotteryService {

	private final RandomNumberService randomNumberService;
	private final int lotteryMax;
	private final int lotterySize;
	
	public StandardLotteryService(RandomNumberService randomNumberService, 
			@Value("${lottery.max}") int lotteryMax, 
			@Value("${lottery.size}") int lotterySize) {
		this.randomNumberService = randomNumberService;
		this.lotteryMax = lotteryMax;
		this.lotterySize = lotterySize;
		System.out.println("%d,%d".formatted(lotteryMax,lotterySize));
	}


	@Override
	public List<LotteryModel> draw(int column) {
		return IntStream.range(0, column)
				        .mapToObj(_ -> this.draw())
				        .toList();
	}

	public LotteryModel draw() {
		return new LotteryModel(
				IntStream.generate(() -> randomNumberService.generate(1,lotteryMax))
		         .distinct()
		         .limit(lotterySize)
		         .sorted()
		         .boxed()
		         .toList());
	}
	
}
