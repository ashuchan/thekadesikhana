package com.dev.backend.service.controller;

import java.math.BigInteger;
import java.security.SecureRandom;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

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
import com.dev.backend.to.OrderTransactionTO.MenuItem;
import com.dev.backend.to.PaymentGatewayTO;
import com.dev.backend.to.WebOrderTO;

@Controller
public class TDKOrderService extends TDKServices {

	private SecureRandom random = new SecureRandom();

	public String nextSessionId() {
		return new BigInteger(130, random).toString(32);
	}
	
	@RequestMapping(value="/order/{orderId}",method=RequestMethod.GET,produces=MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Order getOrder(@PathVariable String orderId) {
		return delegate.getOrder(orderId);
	}
	
	@RequestMapping(value="/order",method=RequestMethod.GET,produces=MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Order getOrderByParam(@RequestParam String orderId) {
		return delegate.getOrder(orderId);
	}
	
	@RequestMapping(value="/order/user/{userId}",method=RequestMethod.GET,produces=MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody List<Order> getOrdersByCustomer(@PathVariable String userId) {
		return delegate.getOrdersByCustomer(userId);
	}
	
	@RequestMapping(value="/order/user",method=RequestMethod.GET,produces=MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody List<Order> getOrdersByCustomerParam(@RequestParam String userId) {
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
		boolean isCod = order.getIsCOD().startsWith("f")?false:true;

		int gatewayPrice = totalPrice
				- (order.getPromotionalWalletCut() + order.getWalletCashCut());
		String transactionId = null;
		String orderId = null;
		String paymentURL = null;
		if (gatewayPrice > 0 && !isCod) {
			InstamojoPaymentTO paymentRequest = new InstamojoPaymentTO(""+gatewayPrice, user.getName(), user.getEmail(), user.getPhone());
			InstamojoPaymentResponseTO response = TDKInstamojoService.createPayment(paymentRequest);
			orderId = TDKInstamojoService.createOrder(response.getId());
			transactionId = response.getId();
			paymentURL = response.getLongurl();
		} else {
			orderId = nextSessionId();
		}
		
		Order orderObj = new Order();
		orderObj.setAddress(address);
		orderObj.setOrderDate(new Date(Instant.now().toEpochMilli()));
		orderObj.setOrderId(orderId);
		orderObj.setTotalPrice(totalPrice);
		orderObj.setUser(user.getPhone());
		orderObj.setStatus(OrderStatus.ACCEPTED);
		menuItems.forEach(m->m.setOrder(orderObj));
		orderObj.setOrderItems(menuItems);
		List<Transaction> transactions = new ArrayList<Transaction>();
		if(order.getPromotionalWalletCut()>0){
			// create promotional wallet transaction
			transactions.add(createTransaction(order.getPromotionalWalletCut(), orderObj, user.getPhone(), null, TransactionCategory.PROMOTIONAL));
		}
		if(order.getWalletCashCut()>0) {
			// create wallet cash cut
			transactions.add(createTransaction(order.getWalletCashCut(), orderObj, user.getPhone(), null, TransactionCategory.WALLET));
		}
		if( gatewayPrice >0) {
			transactions.add(createTransaction(gatewayPrice, orderObj, user.getPhone(), transactionId, TransactionCategory.GATEWAY));
		}
		
		delegate.createOrderWithTransactions(orderObj,transactions);
		PaymentGatewayTO gatewayTO = new PaymentGatewayTO(orderId, paymentURL);
		return gatewayTO;
	}
	
	@RequestMapping(value="/weborder", method=RequestMethod.POST,consumes=MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody PaymentGatewayTO webOrder(@RequestBody WebOrderTO order) {
		OrderTransactionTO orderTo = new OrderTransactionTO();
		orderTo.setUserId("7348815961"); //Sample Web Order User
		
		UserAddress address = new UserAddress();
		address.setUserId("7348815961");
		address.setAddressLine1(order.getUser().getAddress());
		address.setMobileNumber(order.getUser().getContact());
		address.setName(order.getUser().getName());
		delegate.createAddress(address);
		
		orderTo.setAddressId(""+delegate.getTotalAddress());
		order.getOrders().forEach(o -> {
			MenuItem mi = new MenuItem();
			mi.setMenuItem(""+o.getSku());
			mi.setQuantity(o.getQuantity());
			orderTo.getMenuItems().add(mi);
		});
		
		if(order.getPaymentType().equals("online"))
			orderTo.setIsCOD("f");
		
		return createOrder(orderTo);
	}

	public Transaction createTransaction(int totalPrice, Order orderObj,
			String walletId, String transactionId, TransactionCategory category) {
		Transaction transaction = new Transaction();
		transaction.setAmount(totalPrice);
		transaction.setOrder(orderObj.getOrderId());
		transaction.setWallet(walletId);
		transaction.setTransactionId((transactionId==null)?nextSessionId():transactionId);
		transaction.setTransactionCategory(category);
		transaction.setTransactionType(TransactionType.DEBIT);
		return transaction;
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

