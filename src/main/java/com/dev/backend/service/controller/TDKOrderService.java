package com.dev.backend.service.controller;

import java.util.List;

import org.springframework.stereotype.Controller;

import com.dev.backend.delegate.DatabaseDelegate;
import com.dev.backend.dto.Order;

@Controller
public class TDKOrderService {

	private DatabaseDelegate delegate;
	
	public Order getOrder(String orderId) {
		
		return null;
	}
	
	public List<Order> getOrdersByCustomer(String userId) {
		
		return null;
	}
	
	public Order createOrder(Order order) {
		
		return order;
	}
	
	public String updateOrderStatus(String orderId, String status) {
		
		return null;
	}
	
	public String cancelOrder(String orderId) {
		
		return null;
	}
	
	public Order updateOrder (Order order) {
		
		return order;
	}
	
}

