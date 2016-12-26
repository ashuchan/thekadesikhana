package com.dev.backend.dto;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="wallet")
public class Wallet {
	@Id
	@Column(name="user_id")
	private String userId;
	
	@Id
	@Column(name="wallet_id")
	private String walletId;
	
	@Column(name="wallet_balance")
	private int walletBalance;
	
	@Column(name="promotional_balance")
	private int promotionalBalance;

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getWalletId() {
		return walletId;
	}

	public void setWalletId(String walletId) {
		this.walletId = walletId;
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
