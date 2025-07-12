package com.example.hr.service;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperties;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

import com.example.hr.domain.event.HrEvent;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
@ConditionalOnProperty(name="messagingStrategy",havingValue = "amqp")
public class HrEventRabbitProducerService {
	private final RabbitTemplate rabbitTemplate;
	private final ObjectMapper objectMapper;
	private final String exchangeName;

	public HrEventRabbitProducerService(RabbitTemplate rabbitTemplate, ObjectMapper objectMapper, @Value("${exchangeName}") String exchangeName) {
		this.rabbitTemplate = rabbitTemplate;
		this.objectMapper = objectMapper;
		this.exchangeName = exchangeName;
	}

	@EventListener
	public void listenHrEvent(HrEvent hrEvent) throws JsonProcessingException {
		var eventAsJson = objectMapper.writeValueAsString(hrEvent);
		rabbitTemplate.convertAndSend(exchangeName, "",eventAsJson);
	}
}
