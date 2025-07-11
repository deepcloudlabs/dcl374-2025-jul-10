package com.example.lottery.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.ScopedProxyMode;

/*
 lottery.max=1000
lottery.size=2
lottery.features.sorted=true
lottery.features.unique=true
 **/
@ConfigurationProperties(prefix = "lottery")
@RefreshScope(proxyMode = ScopedProxyMode.NO)
public record LotteryConfig(int max, int size, Features features) {
	public record Features(boolean sorted, boolean unique) {
	}
}
