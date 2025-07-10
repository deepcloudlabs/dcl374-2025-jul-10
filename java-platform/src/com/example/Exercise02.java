package com.example;

public class Exercise02 {
	private final int ARR_SIZE = 2 * 1024 * 1024;
	private final int[] testData = new int[ARR_SIZE]; // 8MB

	private void run() {
		System.err.println("Started at : " + System.currentTimeMillis());
		for (var i = 0; i < 15_000; i++) {
			touchEveryLine();
			touchEveryItem();
		}
		System.err.println("Warmup finished: " + System.currentTimeMillis());
		System.err.println("Every Item   Every Line   Difference Difference(%)");
		for (var i = 0; i < 100; i++) {
			var t0 = System.nanoTime();
			touchEveryLine();
			var t1 = System.nanoTime();
			touchEveryItem();
			var t2 = System.nanoTime();
			var elItem = t2 - t1;
			var elLine = t1 - t0;
			var diff = elItem - elLine;
			System.err.println("%12d,%12d,%12d,%3.2f".formatted(elItem, elLine, diff, ((100.0 * elItem) / elLine)));
		}
	}

	private void touchEveryItem() {
		for (var i = 0; i < testData.length; i++) // 2M
			testData[i]++;
	}

	private void touchEveryLine() {
		for (var i = 0; i < testData.length; i += 16) // 128K
			testData[i]++;
	}

	public static void main(String[] args) {
		var c = new Exercise02();
		c.run();
	}
}
