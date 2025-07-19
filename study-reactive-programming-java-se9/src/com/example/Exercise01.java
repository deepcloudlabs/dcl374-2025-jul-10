package com.example;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Exercise01 {
	static final ExecutorService customThreadPool = Executors.newFixedThreadPool(100);
    // main flow
	public static void main(String[] args) {
		System.out.println("Application is just started!");
		var businessService = new BusinessService();
		var response = businessService.workHard();
		businessService.workHardAsync()
		               .thenAcceptAsync(result -> {
		            	   System.out.println("[%s] result is %d".formatted(Thread.currentThread().getName(),result));		            	   
		               },customThreadPool);
		for (var i=0;i<500;++i) {
			System.out.println("[%s] Working hard for another task: %d.".formatted(Thread.currentThread().getName(),i));
			try {TimeUnit.MILLISECONDS.sleep(250);}catch(Exception e) {}
		}
		System.out.println("result is %d".formatted(response));
		customThreadPool.shutdown();
		System.out.println("Application is just completed!");
	}

}

class BusinessService {
	
	public int workHard() {
		try {TimeUnit.SECONDS.sleep(5);}catch(Exception e) {}
		return 42;
	}
	public CompletableFuture<Integer> workHardAsync() {
		return CompletableFuture.supplyAsync(()->{
			System.err.println("[%s] Async work is running...".formatted(Thread.currentThread().getName()));
			try {TimeUnit.SECONDS.sleep(5);}catch(Exception e) {}
			return 42;			
		},Exercise01.customThreadPool);
	}
}