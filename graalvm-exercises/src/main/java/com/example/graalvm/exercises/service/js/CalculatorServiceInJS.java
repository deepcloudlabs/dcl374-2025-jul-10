package com.example.graalvm.exercises.service.js;

import org.graalvm.polyglot.Context;
import org.graalvm.polyglot.Value;
import org.springframework.stereotype.Service;

import com.example.graalvm.exercises.service.CalculatorService;

@Service
public class CalculatorServiceInJS implements CalculatorService {
	private final Context context = Context.create();
	
	@Override
	public Value run(String function, Object... params) {
          var jsFunction = context.eval("js", function);
          return jsFunction.execute(params);
	}

}
