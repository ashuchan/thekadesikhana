package com.dev.backend.dto;

import java.sql.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Order {
	public enum OrderStatus {
		ACCEPTED, IN_KITCHEN, PACKAGING, OUT_FOR_DELIVERY, DELIVERED, CANCELLED
	}
	
	@Id
	@Column(name="order_id")
	@JsonProperty
	private String orderId;
	
	@OneToOne
	@JoinColumn(name="phone")
	@JsonIgnore
	private User user;
	
	@Column(name="order_date")
	@JsonProperty
	private Date orderDate;
	
	@Column(name="total_price")
	@JsonProperty
	private long totalPrice;
	
	@OneToOne
	@JoinColumn(name="address_id")
	@JsonIgnore
	private UserAddress address;
	
	@Column(name="order_status")
	@JsonProperty
	private OrderStatus status;
	
	@Column(name="special_request")
	@JsonProperty
	private String specialRequest;
	
	@OneToMany
	@JoinColumn(name="order_id")
	@JsonIgnore
	private List<OrderItem> orderItems;

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Date getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}

	public long getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(long totalPrice) {
		this.totalPrice = totalPrice;
	}

	public UserAddress getAddress() {
		return address;
	}

	public void setAddress(UserAddress address) {
		this.address = address;
	}

	public OrderStatus getStatus() {
		return status;
	}

	public void setStatus(OrderStatus status) {
		this.status = status;
	}

	public String getSpecialRequest() {
		return specialRequest;
	}

	public void setSpecialRequest(String specialRequest) {
		this.specialRequest = specialRequest;
	}

	public List<OrderItem> getOrderItems() {
		return orderItems;
	}

	public void setOrderItems(List<OrderItem> orderItems) {
		this.orderItems = orderItems;
	}
	
}
