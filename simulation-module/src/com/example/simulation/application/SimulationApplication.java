package com.example.simulation.application;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.ServiceLoader;

import com.example.random.service.QualityLevel;
import com.example.random.service.RandomDoubleService;
import com.example.random.service.RandomIntegerService;
import com.example.random.service.ServiceQuality;
import com.example.simulation.service.SimulationService;
import com.example.simulation.service.business.StandardSimulationService;

public class SimulationApplication {

	public static void main(String[] args) throws FileNotFoundException, IOException {
		var properties = new Properties();
		properties.load(new FileInputStream(new File("src","simulation.properties")));
		var integerServiceQualityLevel = QualityLevel.valueOf(properties.getProperty("integer.service"));
		var doubleServiceQualityLevel = QualityLevel.valueOf(properties.getProperty("double.service"));
		SimulationService simpService = new StandardSimulationService();
		var randomIntegerService = getServiceInstance(RandomIntegerService.class,integerServiceQualityLevel);
		var randomDoubleService = getServiceInstance(RandomDoubleService.class,doubleServiceQualityLevel);
		simpService.setRandomIntegerService(randomIntegerService);
		simpService.setRandomDoubleService(randomDoubleService);
		var randInts = simpService.generateRandomValues(10, 100, 10, true, false);
		System.out.println(randInts);
		var randDoubles = simpService.generateRandomValues(10.0, 100.0, 10, true, false);
		System.out.println(randDoubles);
	}

	public static <S> S getServiceInstance(Class<S> clazz, QualityLevel qualityLevel) {
		var services = ServiceLoader.load(clazz);
		for (var service : services) {
			var serviceClazz = service.getClass();
			if (serviceClazz.isAnnotationPresent(ServiceQuality.class)) {
				var serviceQuality = serviceClazz.getAnnotation(ServiceQuality.class);
                if (serviceQuality.value() == qualityLevel) {
                	return service;
                }
			}
		}
		throw new IllegalArgumentException("Cannot find a service of level %s".formatted(qualityLevel.name()));
	}
}
