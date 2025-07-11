package com.example.aspect;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class ProfilingHandler implements InvocationHandler {
	private final Object target;
	
	public ProfilingHandler(Object target) {
		this.target = target;
	}

	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		var start = System.nanoTime();
		var result = method.invoke(target, args);
		var stop = System.nanoTime();
		var methodName =method.getName();
		System.err.println("%s runs %d ns.".formatted(methodName ,(stop-start)));
		return result;
	}

}
