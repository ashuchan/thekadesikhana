package com.dev.backend.delegate;

import java.util.List;

import com.dev.backend.dto.Menu;
import com.dev.backend.dto.Order;
import com.dev.backend.dto.Transaction;
import com.dev.backend.dto.Transaction.TransactionCategory;
import com.dev.backend.dto.Transaction.TransactionType;
import com.dev.backend.dto.User;
import com.dev.backend.dto.UserActivity;
import com.dev.backend.dto.UserAddress;
import com.dev.backend.dto.Wallet;

public interface DatabaseDelegate {
	
	public List<Menu> getTodaysMenu();
	
	public User getUser(String userId);
	
	public Wallet getUserWallet(String userId);
	
	public List<UserAddress> getUserAddress (String userId);

	public List<UserActivity> getUserActivity(String userId);

	boolean createUser(User user);
	
	public boolean createReferralCashBack(User user);
	
	public Order getOrder(String orderId);

	public List<Order> getOrdersByCustomer(String userId);

	boolean createOrder(Order order);

	public String updateOrderStatus(String orderId, String status);

	public List<Transaction> getTransactionsByWallet(String walletId);

	public List<Transaction> getTransactionsByWallet(String walletId,
			TransactionType debit);

	public List<Transaction> getTransactionsByWallet(String walletId,
			TransactionCategory promotional);

	public Transaction createTransaction(Transaction transaction);

	public String updateTransaction(Transaction transaction);
}
