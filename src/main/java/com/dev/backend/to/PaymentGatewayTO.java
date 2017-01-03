package com.dev.backend.to;

import com.fasterxml.jackson.annotation.JsonProperty;

public class PaymentGatewayTO {
	
	@JsonProperty
	private String orderId;

	@JsonProperty
	private String transactionId;

	@JsonProperty
	private String paymentUrl;

	public PaymentGatewayTO(String orderId, String transactionId,
			String paymentUrl) {
		this.orderId = orderId;
		this.transactionId = transactionId;
		this.paymentUrl = paymentUrl;
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public String getTransactionId() {
		return transactionId;
	}

	public void setTransactionId(String transactionId) {
		this.transactionId = transactionId;
	}

	public String getPaymentUrl() {
		return paymentUrl;
	}

	public void setPaymentUrl(String paymentUrl) {
		this.paymentUrl = paymentUrl;
	}

}
