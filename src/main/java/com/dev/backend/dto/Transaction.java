package com.dev.backend.dto;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="transaction")
public class Transaction {
	
	public enum TransactionType {
		DEBIT ,
		CREDIT
	}
	
	public enum TransactionCategory {
		GATEWAY,
		CASHBACK,
		CANCELATION,
		REFERRAL_BONUS,
		CHAIN_ORDER_BONUS,
		PROMOTIONAL,
		WALLET
	}
	
	@Id
	@Column(name="transaction_id")
	private String transactionId;
	
	@ManyToOne
	@JoinColumn(name="order_id")
	private Order order;
	
	@ManyToOne
	@JoinColumn(name="wallet_id")
	private Wallet wallet;
	
	@Column(name="type")
	private String transactionType;
	
	@Column(name="category")
	private String transactionCategory;
	
	@Column(name="amount")
	private long amount;
	
	@Column(name="comment")
	private String comment;
	
	@Column(name="Instamojo")
	private String gatewayId;
	
	public String getTransactionId() {
		return transactionId;
	}

	public void setTransactionId(String transactionId) {
		this.transactionId = transactionId;
	}

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

	public Wallet getWallet() {
		return wallet;
	}

	public void setWallet(Wallet wallet) {
		this.wallet = wallet;
	}

	public String getTransactionType() {
		return transactionType;
	}

	public void setTransactionType(TransactionType transactionType) {
		this.transactionType = transactionType.name();
	}

	public String getTransactionCategory() {
		return transactionCategory;
	}

	public void setTransactionCategory(TransactionCategory transactionCategory) {
		this.transactionCategory = transactionCategory.name();
	}

	public long getAmount() {
		return amount;
	}

	public void setAmount(long amount) {
		this.amount = amount;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public String getGatewayId() {
		return gatewayId;
	}

	public void setGatewayId(String gatewayId) {
		this.gatewayId = gatewayId;
	}

}
