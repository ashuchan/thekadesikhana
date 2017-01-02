package com.dev.backend.to;

public class InstamojoPaymentTO {

	/* 'purpose': 'FIFA 16',
	  'amount': '2500',
	  'buyer_name': 'John Doe',
	  'email': 'foo@example.com',
	  'phone': '9999999999',
	  'redirect_url': 'http://www.example.com/redirect/',
	  'send_email': 'True',
	  'send_sms': 'True',
	  'webhook': 'http://www.example.com/webhook/',
	  'allow_repeated_payments': 'False',*/
	
	private String purpose;
	
	private String amount;
	
	private String buyer_name;
	
	private String email;
	
	private String phone;
	
	private String redirect_url;
	
	private String send_email = "False";
	
	private String send_sms = "False";
	
	private String webhook;
	
	private String allow_repeated_payments = "False";

	public String getPurpose() {
		return purpose;
	}

	public void setPurpose(String purpose) {
		this.purpose = purpose;
	}

	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}

	public String getBuyer_name() {
		return buyer_name;
	}

	public void setBuyer_name(String buyer_name) {
		this.buyer_name = buyer_name;
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

	public String getRedirect_url() {
		return redirect_url;
	}

	public void setRedirect_url(String redirect_url) {
		this.redirect_url = redirect_url;
	}

	public String getSend_email() {
		return send_email;
	}

	public void setSend_email(String send_email) {
		this.send_email = send_email;
	}

	public String getSend_sms() {
		return send_sms;
	}

	public void setSend_sms(String send_sms) {
		this.send_sms = send_sms;
	}

	public String getWebhook() {
		return webhook;
	}

	public void setWebhook(String webhook) {
		this.webhook = webhook;
	}

	public String getAllow_repeated_payments() {
		return allow_repeated_payments;
	}

	public void setAllow_repeated_payments(String allow_repeated_payments) {
		this.allow_repeated_payments = allow_repeated_payments;
	}
	
}
