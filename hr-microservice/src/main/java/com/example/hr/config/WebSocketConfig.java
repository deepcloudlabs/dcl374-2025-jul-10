package com.example.hr.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

import com.example.hr.service.HrEventWebSocketService;

@Configuration
@EnableWebSocket
public class WebSocketConfig implements WebSocketConfigurer{
	private final HrEventWebSocketService hrEventWebSocketService;
	
	public WebSocketConfig(HrEventWebSocketService hrEventWebSocketService) {
		this.hrEventWebSocketService = hrEventWebSocketService;
	}

	@Override
	public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
		registry.addHandler(hrEventWebSocketService, "/hr-events")
		         .setAllowedOrigins("*");
	}

	
}
