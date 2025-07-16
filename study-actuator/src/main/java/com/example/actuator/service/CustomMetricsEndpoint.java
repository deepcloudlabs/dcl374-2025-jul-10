package com.example.actuator.service;

import org.springframework.boot.actuate.endpoint.annotation.Endpoint;
import org.springframework.boot.actuate.endpoint.annotation.ReadOperation;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
@Endpoint(id = "custommetrics")  // exposed at /actuator/custommetrics
public class CustomMetricsEndpoint {

    @ReadOperation
    public Map<String, Object> getCustomMetrics() {
        // Replace with real data
        return Map.of(
            "activeUsers", 42,
            "jobQueueSize", 12,
            "uptimeSeconds", System.currentTimeMillis() / 1000
        );
    }
}
