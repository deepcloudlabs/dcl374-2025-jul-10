package com.example.hr.adapter;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import com.example.hr.domain.event.HrEvent;
import com.example.hr.infra.EventPublisher;

@Service
public class EventPublisherApplicationEventPublisherAdapter implements EventPublisher {
    private final ApplicationEventPublisher eventPublisher;
	
    public EventPublisherApplicationEventPublisherAdapter(ApplicationEventPublisher eventPublisher) {
		this.eventPublisher = eventPublisher;
	}

	@Override
	public void publishEvent(HrEvent event) {
		eventPublisher.publishEvent(event);
	}

}
