package com.dev.backend.to;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class WebOrderTO {
	
	@JsonProperty
	private UserInfo user;
	
	public class UserInfo {
		
		@JsonProperty
		private String emailId;
		
		@JsonProperty
		private String name;
		
		@JsonProperty
		private String address;
		
		@JsonProperty
		private String contact;
		
		public String getEmailId() {
			return emailId;
		}
		public void setEmailId(String emailId) {
			this.emailId = emailId;
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
	private List<OrderInfo> orders;
	
	public class OrderInfo {
		
		@JsonProperty
		private int price;
		
		@JsonProperty
		private int quantity;
		
		@JsonProperty
		private String type;
		
		@JsonProperty
		private int sku;
		
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
	
	public UserInfo getUser() {
		return user;
	}

	public void setUser(UserInfo user) {
		this.user = user;
	}

	public List<OrderInfo> getOrders() {
		return orders;
	}

	public void setOrders(List<OrderInfo> orders) {
		this.orders = orders;
	}

	public String getPaymentType() {
		return paymentType;
	}

	public void setPaymentType(String paymentType) {
		this.paymentType = paymentType;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	@JsonProperty
	private int price;

}
