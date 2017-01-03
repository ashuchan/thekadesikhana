package com.dev.backend.to;

import com.fasterxml.jackson.annotation.JsonProperty;

public class PaymentGatewayTO {
	
	@JsonProperty
	private String orderId;

	@JsonProperty
	private String paymentUrl;

	public PaymentGatewayTO(String orderId, String paymentUrl) {
		this.orderId = orderId;
		this.paymentUrl = paymentUrl;
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public String getPaymentUrl() {
		return paymentUrl;
	}

	public void setPaymentUrl(String paymentUrl) {
		this.paymentUrl = paymentUrl;
	}

}
