package com.dev.backend.dto;

import java.sql.Date;
import java.util.List;

public class Order {
	public enum OrderStatus {
		ACCEPTED, IN_KITCHEN, PACKAGING, OUT_FOR_DELIVERY, DELIVERED, CANCELLED
	}
	
	private String orderId;
	
	private User user;
	
	private Date orderDate;
	
	private long totalPrice;
	
	private UserAddress address;
	
	private OrderStatus status;
	
	private String specialRequest;
	
	private List<OrderItem> orderItems;
	
}
