package com.dev.backend.to;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class WebOrderTO {
	
	@JsonProperty
	private UserInfo userInfo;
	
	public static class UserInfo {
		
		@JsonProperty
		private String mail;
		
		@JsonProperty
		private String name;
		
		@JsonProperty
		private String address;
		
		@JsonProperty
		private String contact;
		
		public UserInfo() {
		}
		
		public UserInfo(String mail, String name, String address, String contact) {
			super();
			this.mail = mail;
			this.name = name;
			this.address = address;
			this.contact = contact;
		}

		public String getMail() {
			return mail;
		}
		public void setMail(String emailId) {
			this.mail = emailId;
		}
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public String getAddress() {
			return address;
		}
		public void setAddress(String address) {
			this.address = address;
		}
		public String getContact() {
			return contact;
		}
		public void setContact(String contact) {
			this.contact = contact;
		}
	}
	
	@JsonProperty
	private List<OrderInfo> orderInfo;
	
	public static class OrderInfo {
		
		@JsonProperty
		private int price;
		
		@JsonProperty
		private int quantity;
		
		@JsonProperty
		private String type;
		
		@JsonProperty
		private int sku;
		
		public OrderInfo() {
		}
		
		public OrderInfo(int price, int quantity, String type, int sku) {
			super();
			this.price = price;
			this.quantity = quantity;
			this.type = type;
			this.sku = sku;
		}

		public int getPrice() {
			return price;
		}
		public void setPrice(int price) {
			this.price = price;
		}
		public int getQuantity() {
			return quantity;
		}
		public void setQuantity(int quantity) {
			this.quantity = quantity;
		}
		public String getType() {
			return type;
		}
		public void setType(String type) {
			this.type = type;
		}
		public int getSku() {
			return sku;
		}
		public void setSku(int sku) {
			this.sku = sku;
		}
	}
	
	@JsonProperty
	private String paymentType;
	
	public UserInfo getUserInfo() {
		return userInfo;
	}

	public void setUserInfo(UserInfo user) {
		this.userInfo = user;
	}

	public List<OrderInfo> getOrders() {
		return orderInfo;
	}

	public void setOrders(List<OrderInfo> orders) {
		this.orderInfo = orders;
	}

	public String getPaymentType() {
		return paymentType;
	}

	public void setPaymentType(String paymentType) {
		this.paymentType = paymentType;
	}

	public int getTotalDue() {
		return totalDue;
	}

	public void setTotalDue(int price) {
		this.totalDue = price;
	}

	@JsonProperty
	private int totalDue;

}
