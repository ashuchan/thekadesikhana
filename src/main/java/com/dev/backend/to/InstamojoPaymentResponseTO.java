package com.dev.backend.to;

import com.fasterxml.jackson.annotation.JsonProperty;

public class InstamojoPaymentResponseTO {
	/*
	 * {
    "id": "f3998499818d43ff96009efabdd898f5", 
    "user": "https://api.instamojo.com/v2/users/2cdaf29c8b2743319fbf149092dbd198/", 
    "phone": "+917348815961", 
    "email": "sales@thekadesikhana.com", 
    "buyer_name": "WebUser", 
    "amount": "396.00", 
    "purpose": "ThekaDesiMeals", 
    "status": "Pending", 
    "expires_at": null, 
    "payments": [], 
    "send_sms": false, 
    "send_email": false, 
    "sms_status": null, 
    "email_status": null, 
    "shorturl": null, 
    "longurl": "https://www.instamojo.com/@thekadesikhana/f3998499818d43ff96009efabdd898f5", 
    "redirect_url": null, 
    "webhook": "", 
    "scheduled_at": null, 
    "created_at": "2017-03-26T18:03:30.954282Z", 
    "modified_at": "2017-03-26T18:03:30.954316Z", 
    "resource_uri": "https://api.instamojo.com/v2/payment_requests/f3998499818d43ff96009efabdd898f5/", 
    "allow_repeated_payments": false, 
    "mark_fulfilled": true
}
	 */
	
	@JsonProperty
	private String expires_at;
	
	@JsonProperty
	private String scheduled_at;
	
	@JsonProperty
	private String send_email;
	
	@JsonProperty
	private String id;

	@JsonProperty
	private String user;

	@JsonProperty
	private String phone;

	@JsonProperty
	private String email;

	@JsonProperty
	private String buyer_name;

	@JsonProperty
	private String send_sms;

	@JsonProperty
	private String sms_status;

	@JsonProperty
	private String email_status;

	@JsonProperty
	private String shorturl;

	@JsonProperty
	private String redirect_url;

	@JsonProperty
	private String webhook;

	@JsonProperty
	private String purpose;
	
	@JsonProperty
	private String allow_repeated_payments;

	@JsonProperty
	private String mark_fulfilled;

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

	public String getPurpose() {
		return purpose;
	}

	public void setPurpose(String purpose) {
		this.purpose = purpose;
	}

	public String getResource_uri() {
		return resource_uri;
	}

	public void setResource_uri(String resource_uri) {
		this.resource_uri = resource_uri;
	}

	public String getBuyer_name() {
		return buyer_name;
	}

	public void setBuyer_name(String buyer_name) {
		this.buyer_name = buyer_name;
	}

	public String getSend_sms() {
		return send_sms;
	}

	public void setSend_sms(String send_sms) {
		this.send_sms = send_sms;
	}

	public String getSms_status() {
		return sms_status;
	}

	public void setSms_status(String sms_status) {
		this.sms_status = sms_status;
	}

	public String getEmail_status() {
		return email_status;
	}

	public void setEmail_status(String email_status) {
		this.email_status = email_status;
	}

	public String getShorturl() {
		return shorturl;
	}

	public void setShorturl(String shorturl) {
		this.shorturl = shorturl;
	}

	public String getRedirect_url() {
		return redirect_url;
	}

	public void setRedirect_url(String redirect_url) {
		this.redirect_url = redirect_url;
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

	public String getMark_fulfilled() {
		return mark_fulfilled;
	}

	public void setMark_fulfilled(String mark_fulfilled) {
		this.mark_fulfilled = mark_fulfilled;
	}

	public String getExpires_at() {
		return expires_at;
	}

	public void setExpires_at(String expires_at) {
		this.expires_at = expires_at;
	}

	public String getScheduled_at() {
		return scheduled_at;
	}

	public void setScheduled_at(String scheduled_at) {
		this.scheduled_at = scheduled_at;
	}

	public String getSend_email() {
		return send_email;
	}

	public void setSend_email(String send_email) {
		this.send_email = send_email;
	}

}
