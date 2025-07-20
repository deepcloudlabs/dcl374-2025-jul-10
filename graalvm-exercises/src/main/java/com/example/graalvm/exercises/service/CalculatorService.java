package com.example.graalvm.exercises.service;

import org.graalvm.polyglot.Value;

public interface CalculatorService {
	Value run(String function, Object... params);
}
