package com.ai.prediction.engine;

import java.util.UUID;

import org.junit.Test;

import com.ai.prediction.model.Claim;
import com.ai.prediction.model.Claim.ProviderType;

public class ClaimAnalyzerTest {

	@Test
	public void test() {
		ClaimAnalyzer analyzer = new ClaimAnalyzer();
		
		Claim claim = new Claim();
		claim.setClaimId(UUID.randomUUID());
		claim.setProviderName("ravikiran");
		claim.setProviderType(ProviderType.IN_NETWORK);
		claim.setClaimAmount(3500);
		
		System.out.println(claim);
		System.out.println(analyzer.analyzeClaim(claim).isProvDetailsReq());
	}

}
