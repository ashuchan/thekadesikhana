package com.dev.backend.to;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

//userId, addressId, list of MenuItems and other details from the request
public class OrderTransactionTO {
	
	public class MenuItem {
		
		private String menuItem;
		
		private int quantity;

		public String getMenuItem() {
			return menuItem;
		}

		public void setMenuItem(String menuItem) {
			this.menuItem = menuItem;
		}

		public int getQuantity() {
			return quantity;
		}

		public void setQuantity(int quantity) {
			this.quantity = quantity;
		}
		
	}

	@JsonProperty
	private String userId;
	
	@JsonProperty
	private String addressId;
	
	@JsonProperty
	private List<MenuItem> menuItems;

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getAddressId() {
		return addressId;
	}

	public void setAddressId(String addressId) {
		this.addressId = addressId;
	}

	public List<MenuItem> getMenuItems() {
		return menuItems;
	}

	public void setMenuItems(List<MenuItem> menuItems) {
		this.menuItems = menuItems;
	}
}
