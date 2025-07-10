package com.example.lottery;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

import com.example.lottery.config.LotteryConfig;

@SpringBootApplication
@EnableConfigurationProperties(LotteryConfig.class)
@ConfigurationPropertiesScan
public class LotteryWebApplication {

	public static void main(String[] args) {
		SpringApplication.run(LotteryWebApplication.class, args);
	}

}
