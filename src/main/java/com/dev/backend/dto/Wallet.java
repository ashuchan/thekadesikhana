package com.dev.backend.dto;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="wallet")
public class Wallet implements Serializable{
	
	@Id
	@Column(name="phone")
	@JsonIgnore
	private String user;
	
	@Column(name="wallet_balance")
	private int walletBalance;
	
	@Column(name="promotional_balance")
	private int promotionalBalance;
	
	public String getUser() {
		return user;
	}

	public void setUser(String userId) {
		this.user = userId;
	}

	public int getWalletBalance() {
		return walletBalance;
	}

	public void setWalletBalance(int walletBalance) {
		this.walletBalance = walletBalance;
	}

	public int getPromotionalBalance() {
		return promotionalBalance;
	}

	public void setPromotionalBalance(int promotionalBalance) {
		this.promotionalBalance = promotionalBalance;
	}
	
}
