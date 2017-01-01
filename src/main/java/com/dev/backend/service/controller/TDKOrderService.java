package com.dev.backend.service.controller;

import java.util.List;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dev.backend.delegate.DatabaseDelegate;
import com.dev.backend.dto.Order;
import com.dev.backend.util.UserUtil;

@Controller
public class TDKOrderService extends TDKServices {
	
	@RequestMapping(value="/order/{orderId}",method=RequestMethod.GET,produces=MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Order getOrder(@PathVariable String orderId) {
		return delegate.getOrder(orderId);
	}
	
	@RequestMapping(value="/order/user/{userId}",method=RequestMethod.GET,produces=MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody List<Order> getOrdersByCustomer(@PathVariable String userId) {
		return delegate.getOrdersByCustomer(userId);
	}
	
	@RequestMapping(value="/order", method=RequestMethod.POST,consumes=MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Order createOrder(@RequestBody Order order) {
		/*
		 * Generate new Order
		 * Update Database
		 * Generate OrderItems
		 */
		//UserUtil.generateUserAccessInfo(user);
		delegate.createOrder(order);
		
		return order;
	}
	
	@RequestMapping(value="/order/status", method=RequestMethod.POST,consumes=MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody String updateOrderStatus(@RequestBody String orderId, @RequestBody String status) {
		delegate.updateOrderStatus(orderId, status);
		return status;
	}
	
	public String cancelOrder(String orderId) {
		
		return null;
	}
	
	public Order updateOrder (Order order) {
		
		return order;
	}
	
}

