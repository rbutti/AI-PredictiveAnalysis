package com.ai.prediction.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ai.prediction.engine.ClaimAnalyzer;
import com.ai.prediction.model.Claim;

@Service
public class AIPredictiveAnalysisService {

	@Autowired
	ClaimAnalyzer claimAnalyzer;
	
	public Claim analyzeClaim(Claim claim) {
		return claimAnalyzer.analyzeClaim(claim);
	}
}
