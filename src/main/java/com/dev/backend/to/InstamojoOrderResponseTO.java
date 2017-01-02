package com.dev.backend.to;

import com.fasterxml.jackson.annotation.JsonProperty;

public class InstamojoOrderResponseTO {
	
	/*
	 * "order_id": "3b56d216-74c2-4189-83f3-9c2e93bad1be",
	 * "name": "Vedhavyas",
	  "email": "vedhavyas@instamojo.com",
	  "phone": "+919663445546",
	  "amount": "100.00"
	 */

	@JsonProperty
	private String order_id;

	@JsonProperty
	private String name;

	@JsonProperty
	private String email;

	@JsonProperty
	private String phone;

	@JsonProperty
	private String amount;

	public String getOrder_id() {
		return order_id;
	}

	public void setOrder_id(String order_id) {
		this.order_id = order_id;
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

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}
}
