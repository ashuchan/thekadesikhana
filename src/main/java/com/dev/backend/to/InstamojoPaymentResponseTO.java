package com.dev.backend.to;

import com.fasterxml.jackson.annotation.JsonProperty;

public class InstamojoPaymentResponseTO {
	/*
	 * {
  "id": "05f317448ad84649aa1a9c7328edb015",
  "user": "https://api.instamojo.com/v2/users/90f01dfdacbe4fe7892fc27dbdc30906/",
  "phone": "+919999999999",
  "email": "foo@example.com",
  "buyer_name": "John Doe",
  "amount": "2500",
  "purpose": "FIFA 16",
  "status": "Pending",
  "payments": [],
  "send_sms": true,
  "send_email": true,
  "sms_status": "Pending",
  "email_status": "Pending",
  "shorturl": null,
  "longurl": "https://www.instamojo.com/@foo/05f317448ad84649aa1a9c7328edb015",
  "redirect_url": "http://www.example.com/redirect/",
  "webhook": "http://www.example.com/webhook/",
  "created_at": "2016-05-09T16:10:13.786Z",
  "modified_at": "2016-05-09T16:10:13.786Z",
  "resource_uri": "https://api.instamojo.com/v2/payment_requests/05f317448ad84649aa1a9c7328edb015/",
  "allow_repeated_payments": false,
  "mark_fulfilled": true
}
	 */
	
	@JsonProperty
	private String id;

	@JsonProperty
	private String user;

	@JsonProperty
	private String phone;

	@JsonProperty
	private String email;

	@JsonProperty
	private String status;

	@JsonProperty
	private String amount;

	@JsonProperty
	private String[] payments;

	@JsonProperty
	private String longurl;

	@JsonProperty
	private String created_at;

	@JsonProperty
	private String modified_at;

	@JsonProperty
	private String resource_uri;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}

	public String[] getPayments() {
		return payments;
	}

	public void setPayments(String[] payments) {
		this.payments = payments;
	}

	public String getLongurl() {
		return longurl;
	}

	public void setLongurl(String longurl) {
		this.longurl = longurl;
	}

	public String getCreated_at() {
		return created_at;
	}

	public void setCreated_at(String created_at) {
		this.created_at = created_at;
	}

	public String getModified_at() {
		return modified_at;
	}

	public void setModified_at(String modified_at) {
		this.modified_at = modified_at;
	}

	public String getResource_uri() {
		return resource_uri;
	}

	public void setResource_uri(String resource_uri) {
		this.resource_uri = resource_uri;
	}

}
