package com.example.service;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class BusinessService {

	@Async 
	public CompletableFuture<Integer> fun() { // asynchronous
			System.err.println(Thread.currentThread().getName()+" is running BusinessService::fun");
			try { TimeUnit.SECONDS.sleep(ThreadLocalRandom.current().nextInt(1, 3));}catch(Exception e) {}
			return CompletableFuture.completedFuture(42);

	}
}
