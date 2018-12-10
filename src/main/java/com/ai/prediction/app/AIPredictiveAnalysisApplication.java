package com.ai.prediction.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication 
@ComponentScan(basePackages= {"com.ai.prediction"})
public class AIPredictiveAnalysisApplication {

	public static void main(String[] args) {
		SpringApplication.run(AIPredictiveAnalysisApplication.class, args);
	}
}
