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
	@OneToOne
	@JoinColumn(name="phone")
	@JsonIgnore
	private User user;
	
	@Column(name="wallet_balance")
	private int walletBalance;
	
	@Column(name="promotional_balance")
	private int promotionalBalance;
	
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="wallet_id")
	private int walletId;

	public User getUser() {
		return user;
	}

	public void setUser(User userId) {
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

	public int getWalletId() {
		return walletId;
	}

	public void setWalletId(int walletId) {
		this.walletId = walletId;
	}
}
