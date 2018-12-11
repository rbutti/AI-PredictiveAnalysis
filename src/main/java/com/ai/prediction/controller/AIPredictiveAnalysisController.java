package com.ai.prediction.controller;

import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ai.prediction.model.Claim;
import com.ai.prediction.service.AIPredictiveAnalysisService;

@RestController
public class AIPredictiveAnalysisController {
	
	@Autowired
	AIPredictiveAnalysisService aiService;

	@PostMapping(value = "/claims", produces = MediaType.APPLICATION_JSON, consumes = MediaType.APPLICATION_JSON)
	public Claim analyzeClaim(@RequestBody Claim claim) {
		return aiService.analyzeClaim(claim);
	}

}
