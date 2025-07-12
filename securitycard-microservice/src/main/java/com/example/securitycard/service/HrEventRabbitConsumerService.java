package com.example.securitycard.service;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
public class HrEventRabbitConsumerService {

	@RabbitListener(queues = "hrque")
	public void listenHrEvents(String hrEventAsJson) {
		System.err.println("New event has arrived from Rabbit: %s".formatted(hrEventAsJson));
	}
}
