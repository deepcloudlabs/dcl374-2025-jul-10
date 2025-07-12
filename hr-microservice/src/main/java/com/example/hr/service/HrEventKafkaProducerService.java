package com.example.hr.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.event.EventListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.example.hr.domain.event.HrEvent;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class HrEventKafkaProducerService {
	private final KafkaTemplate<String, String> kafkaTemplate;
	private final ObjectMapper objectMapper;
	private final String topicName;

	public HrEventKafkaProducerService(KafkaTemplate<String, String> kafkaTemplate, ObjectMapper objectMapper, @Value("${topicName}") String topicName) {
		this.kafkaTemplate = kafkaTemplate;
		this.objectMapper = objectMapper;
		this.topicName = topicName;
	}

	@EventListener
	public void listenHrEvent(HrEvent hrEvent) throws JsonProcessingException {
		var eventAsJson = objectMapper.writeValueAsString(hrEvent);
		kafkaTemplate.send(topicName, eventAsJson);
	}
}
