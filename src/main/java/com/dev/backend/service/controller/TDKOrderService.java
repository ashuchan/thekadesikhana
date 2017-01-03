package com.dev.backend.service.controller;

import java.sql.Date;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dev.backend.delegate.DatabaseDelegate;
import com.dev.backend.dto.Menu;
import com.dev.backend.dto.Order;
import com.dev.backend.dto.Order.OrderStatus;
import com.dev.backend.dto.OrderItem;
import com.dev.backend.dto.Transaction;
import com.dev.backend.dto.Transaction.TransactionCategory;
import com.dev.backend.dto.Transaction.TransactionType;
import com.dev.backend.dto.User;
import com.dev.backend.dto.UserAddress;
import com.dev.backend.to.InstamojoPaymentResponseTO;
import com.dev.backend.to.InstamojoPaymentTO;
import com.dev.backend.to.OrderTransactionTO;
import com.dev.backend.to.PaymentGatewayTO;
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
	public @ResponseBody PaymentGatewayTO createOrder(@RequestBody OrderTransactionTO order) {
		/*
		 * Create new payment with instamojo
		 * Create new order
		 * Generate new Order with order id from instamojo
		 * create transaction with payment id
		 * Update Database
		 * return object with payment details
		 */
		//UserUtil.generateUserAccessInfo(user);
		//delegate.createOrder(order);
		User user = delegate.getUser(order.getUserId());
		UserAddress address = delegate.getAddressById(order.getAddressId());
		List<OrderItem> menuItems = new ArrayList<OrderItem>();
		order.getMenuItems().forEach(
				m -> menuItems.add(new OrderItem(delegate.getMenuItemById(m
						.getMenuItem()), m.getQuantity())));
		int totalPrice = 0;
		for(OrderItem m:menuItems) {
			totalPrice+=m.getMenu().getPrice()*m.getQuantity();
		}
		InstamojoPaymentTO paymentRequest = new InstamojoPaymentTO(""+totalPrice, user.getName(), user.getEmail(), user.getPhone());
		InstamojoPaymentResponseTO response = TDKInstamojoService.createPayment(paymentRequest);
		String orderId = TDKInstamojoService.createOrder(response.getId());
		Order orderObj = new Order();
		orderObj.setAddress(address);
		orderObj.setOrderDate(new Date(Instant.now().toEpochMilli()));
		orderObj.setOrderId(orderId);
		orderObj.setTotalPrice(totalPrice);
		orderObj.setUser(user);
		orderObj.setStatus(OrderStatus.ACCEPTED);
		menuItems.forEach(m->m.setOrder(orderObj));
		orderObj.setOrderItems(menuItems);
		Transaction transaction = new Transaction();
		transaction.setAmount(totalPrice);
		transaction.setOrder(orderObj);
		transaction.setWallet(user.getWallet());
		transaction.setTransactionId(response.getId());
		transaction.setTransactionCategory(TransactionCategory.GATEWAY);
		transaction.setTransactionType(TransactionType.DEBIT);
		delegate.createOrder(orderObj,transaction);
		PaymentGatewayTO gatewayTO = new PaymentGatewayTO(orderId, response.getId(), response.getLongurl());
		return gatewayTO;
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

