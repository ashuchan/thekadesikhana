package com.dev.backend.dto;

import java.sql.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table(name="`order`")
public class Order {

	public enum OrderStatus {
		ACCEPTED, IN_KITCHEN, PACKAGING, OUT_FOR_DELIVERY, DELIVERED, CANCELLED
	}
	
	@Id
	@Column(name="`order_id`")
	@JsonProperty
	private String orderId;
	
	@Column(name="`user_id`")
	@JsonProperty
	private String user;
	
	@Column(name="`date`")
	@JsonProperty
	private Date orderDate;
	
	@Column(name="`price`")
	@JsonProperty
	private long totalPrice;
	
	@OneToOne
	@JoinColumn(name="`address_id`")
	@JsonProperty
	private UserAddress address;
	
	@Column(name="`order_status`")
	@JsonProperty
	private String status;
	
	@Column(name="`special_request`")
	@JsonProperty
	private String specialRequest;
	
	@OneToMany(fetch=FetchType.EAGER,cascade=CascadeType.ALL,orphanRemoval=true)
	@JoinColumn(name="`order_id`")
	@JsonProperty
	private List<OrderItem> orderItems;

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
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

	public String getStatus() {
		return status;
	}

	public void setStatus(OrderStatus status) {
		this.status = status.name();
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
