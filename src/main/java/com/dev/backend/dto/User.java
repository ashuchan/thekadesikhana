package com.dev.backend.dto;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table(name="user")
public class User implements Serializable{
	
	@Column(name="name")
	@JsonProperty
	private String name;
	
	@Column(name="email")
	@JsonProperty
	private String email;
	
	@Id
	@Column(name="phone")
	@JsonProperty
	private String phone;
	
	@Column(name="gender")
	@JsonProperty
	private String gender;
	
	@Column(name="referral_code")
	@JsonProperty
	private String referralCode;
	
	@Column(name="referee_code")
	@JsonProperty
	private String refereeCode;
	
	@OneToOne(fetch=FetchType.EAGER,cascade=CascadeType.ALL,orphanRemoval=true)
	@JoinColumn(name="phone")
	@JsonProperty
	private Token accessTokens;

	@JoinColumn(name="dob")
	@JsonProperty
	private Date dob;
	
	@OneToOne(fetch=FetchType.EAGER,cascade=CascadeType.ALL,orphanRemoval=true)
	@JoinColumn(name="phone")
	private Wallet wallet;
	
	public Date getDob() {
		return dob;
	}

	public void setDob(Date dob) {
		this.dob = dob;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String mobileNumber) {
		this.phone = mobileNumber;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getReferralCode() {
		return referralCode;
	}

	@JsonIgnore
	public void setReferralCode(String referralCode) {
		this.referralCode = referralCode;
	}

	public Token getAccessTokens() {
		return accessTokens;
	}

	@JsonIgnore
	public void setAccessTokens(Token accessTokens) {
		this.accessTokens = accessTokens;
	}

	public Wallet getWallet() {
		return wallet;
	}

	public void setWallet(Wallet wallet) {
		this.wallet = wallet;
	}

	public String getRefereeCode() {
		return refereeCode;
	}

	public void setRefereeCode(String refereeCode) {
		this.refereeCode = refereeCode;
	}

}
