package com.dev.backend.to;

import com.fasterxml.jackson.annotation.JsonProperty;

public class InstamojoAccessTokenTO {
	
	@JsonProperty(value="access_token")
	private String accessToken;
	
	@JsonProperty(value="token_type")
	private String tokenType;
	
	@JsonProperty(value="expires_in")
	private String expiresIn;
	
	@JsonProperty
	private String scope;
	
	@JsonProperty
	private String error;

	public String getAccessToken() {
		return accessToken;
	}

	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}

	public String getTokenType() {
		return tokenType;
	}

	public void setTokenType(String tokenType) {
		this.tokenType = tokenType;
	}

	public String getExpiresIn() {
		return expiresIn;
	}

	public void setExpiresIn(String expiresIn) {
		this.expiresIn = expiresIn;
	}

	public String getScope() {
		return scope;
	}

	public void setScope(String scope) {
		this.scope = scope;
	}

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}

}
