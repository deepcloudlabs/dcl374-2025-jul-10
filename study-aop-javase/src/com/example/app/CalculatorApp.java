package com.example.app;

import java.lang.reflect.Proxy;

import com.example.aspect.ProfilingHandler;
import com.example.service.Calculator;
import com.example.service.StandardCalculator;

public class CalculatorApp {

	public static void main(String[] args) {
		var calculator = new StandardCalculator();
		var clazz = calculator.getClass();
		var proxy = (Calculator) Proxy.newProxyInstance(clazz.getClassLoader(), clazz.getInterfaces(), new ProfilingHandler(calculator));
        calculator.setSelf(proxy);
		System.out.println("calculator: %s".formatted(proxy.getClass()));
		System.out.println("4 * 7 = %f".formatted(proxy.mul(4, 7)));
	}

}
