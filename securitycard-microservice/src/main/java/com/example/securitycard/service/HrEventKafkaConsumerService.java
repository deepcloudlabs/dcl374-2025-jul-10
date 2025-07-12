package com.example.securitycard.service;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class HrEventKafkaConsumerService {

	@KafkaListener(groupId = "securitycard", topics = "hr-events")
	public void handleHrEvent(String hrEventAsJson) {
		System.err.println("New event has arrived from Kafka: %s".formatted(hrEventAsJson));
	}
}
