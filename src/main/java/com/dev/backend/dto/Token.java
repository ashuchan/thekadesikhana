package com.dev.backend.dto;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table(name="tokens")
public class Token implements Serializable{

	@Id
	@Column(name="phone")
	@JsonIgnore
	private String user;
	
	@Column(name="access_token")
	@JsonProperty
	private String acessToken;
	
	@Column(name="refresh_token")
	@JsonProperty
	private String refreshToken;
	
	@Column(name="acess_token_expiry")
	@JsonProperty
	private long accessTokenExpiry;
	
	@Column(name="refresh_token_expiry")
	private long refreshTokenExpiry;

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getAcessToken() {
		return acessToken;
	}

	public void setAcessToken(String acessToken) {
		this.acessToken = acessToken;
	}

	public String getRefreshToken() {
		return refreshToken;
	}

	public void setRefreshToken(String refreshToken) {
		this.refreshToken = refreshToken;
	}

	public long getAccessTokenExpiry() {
		return accessTokenExpiry;
	}

	public void setAccessTokenExpiry(long accessTokenExpiry) {
		this.accessTokenExpiry = accessTokenExpiry;
	}

	public long getRefreshTokenExpiry() {
		return refreshTokenExpiry;
	}

	public void setRefreshTokenExpiry(long refreshTokenExpiry) {
		this.refreshTokenExpiry = refreshTokenExpiry;
	}

}
