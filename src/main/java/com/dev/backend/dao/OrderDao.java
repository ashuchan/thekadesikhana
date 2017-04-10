package com.dev.backend.dao;

import java.util.List;

import com.dev.backend.dto.Order;

public interface OrderDao {

	public Order getOrder(String orderId);

	public List<Order> getOrdersByCustomer(String userId);

	public boolean createOrder(Order order);

	public String updateOrderStatus(String orderId, String status);

	public Order getOrderByTransaction(String transactionId);

}
