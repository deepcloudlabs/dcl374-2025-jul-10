package com.example;

public class Exercise01 {

	public static void main(String[] args) throws InterruptedException {
		var start = System.currentTimeMillis();
		for (var i = 0; i < 2_000; i++) {
			Thread.sleep(2);
		}
		var end = System.currentTimeMillis();
		System.out.println("Millis elapsed (%): " + (end - start) / 40.0);
		System.out.println("Millis elapsed: " + (end - start));

	}

}
