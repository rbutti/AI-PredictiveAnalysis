package com.ai.prediction.model;

import java.io.Serializable;
import java.util.UUID;

public class Claim implements Serializable {

	private static final long serialVersionUID = -2578750383190124007L;
	private UUID claimId;
	private String clientName;
	private String providerName;
	private float claimAmount;
	private boolean provDetailsReq;
	private ProviderType providerType;

	public UUID getClaimId() {
		return claimId;
	}

	public void setClaimId(UUID claimId) {
		this.claimId = claimId;
	}

	public String getProviderName() {
		return providerName;
	}

	public void setProviderName(String providerName) {
		this.providerName = providerName;
	}

	public float getClaimAmount() {
		return claimAmount;
	}

	public void setClaimAmount(float claimAmount) {
		this.claimAmount = claimAmount;
	}

	public boolean isProvDetailsReq() {
		return provDetailsReq;
	}

	public void setProvDetailsReq(boolean provDetailsReq) {
		this.provDetailsReq = provDetailsReq;
	}

	public ProviderType getProviderType() {
		return providerType;
	}

	public void setProviderType(ProviderType providerType) {
		this.providerType = providerType;
	}

	public String getClientName() {
		return clientName;
	}

	public void setClientName(String clientName) {
		this.clientName = clientName;
	}

	public enum ProviderType {
		IN_NETWORK, OUT_NETWORK;
	}

	@Override
	public String toString() {
		return "Claim [claimId=" + claimId + ", clientName=" + clientName + ", providerName=" + providerName
				+ ", claimAmount=" + claimAmount + ", provDetailsReq=" + provDetailsReq + ", providerType="
				+ providerType + "]";
	}

}
