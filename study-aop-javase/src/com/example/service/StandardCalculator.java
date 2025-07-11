package com.example.service;

public class StandardCalculator implements Calculator {
	private Calculator self;

	public StandardCalculator() {
		self = this;
	}

	public void setSelf(Calculator self) {
		this.self = self;
	}

	@Override
	public double add(double x, double y) {
		return x + y;
	}

	@Override
	public double sub(double x, double y) {
		return x - y;
	}

	@Override
	public double mul(double x, double y) {
		var sum = 0.0;
		for (var i = 0.0; i < x; i++) {
			sum = self.add(sum, y);
		}
		return sum;
	}

	@Override
	public double div(double x, double y) {
		return x / y;
	}

}
