package com.example.securitycard.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.WebSocketMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.client.WebSocketClient;

import jakarta.annotation.PostConstruct;

@Service
public class HrServiceWebSocketConsumerService implements WebSocketHandler {
	private final WebSocketClient webSocketClient;
	private final String hrWSServiceURL;
	
	public HrServiceWebSocketConsumerService(WebSocketClient webSocketClient,@Value("${hrWsUrl}") String hrWSServiceURL) {
		this.webSocketClient = webSocketClient;
		this.hrWSServiceURL = hrWSServiceURL;
	}

	@PostConstruct
	public void connectToWSService() {
		webSocketClient.execute(this, hrWSServiceURL);
	}

	@Override
	public void afterConnectionEstablished(WebSocketSession session) throws Exception {
		System.err.println("Successfully connected to the HR WS Service: %s".formatted(session.getId()));
	}

	@Override
	public void handleMessage(WebSocketSession session, WebSocketMessage<?> message) throws Exception {
		System.err.println("New event has arrived from WS Endpoint: %s".formatted(message.getPayload()));		
	}

	@Override
	public void handleTransportError(WebSocketSession session, Throwable e) throws Exception {
		System.err.println("An error has occured [%s]: %s".formatted(session.getId(),e.getMessage()));		
	}

	@Override
	public void afterConnectionClosed(WebSocketSession session, CloseStatus closeStatus) throws Exception {
		System.err.println("Connection is closed for the session [%s]: %s".formatted(session.getId(),closeStatus));		
	}

	@Override
	public boolean supportsPartialMessages() {
		return false;
	}
}
