package com.example.lottery.service;

import java.util.List;

import com.example.lottery.dto.LotteryModel;

public interface LotteryService {

	List<LotteryModel> draw(int column);

}
