package com.example.lottery;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

// clean install spring-boot:build-image
// docker run -p 8500:7500 -e server_address=0.0.0.0 sha256:d4f42d80227cc02f890f5d1c9b28922ac69a51b325d9fbafcff15b1f30eb217f
@SpringBootApplication
public class GraalvmNativeImageSpringBootApplication {

	public static void main(String[] args) {
		SpringApplication.run(GraalvmNativeImageSpringBootApplication.class, args);
	}

}
