package com.deve.backend.delegate.impl;

import java.time.Instant;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.dev.backend.dao.MenuDao;
import com.dev.backend.dao.OrderDao;
import com.dev.backend.dao.TransactionDao;
import com.dev.backend.dao.UserDao;
import com.dev.backend.delegate.DatabaseDelegate;
import com.dev.backend.dto.Menu;
import com.dev.backend.dto.Order;
import com.dev.backend.dto.Transaction;
import com.dev.backend.dto.Transaction.TransactionCategory;
import com.dev.backend.dto.Transaction.TransactionType;
import com.dev.backend.dto.User;
import com.dev.backend.dto.UserActivity;
import com.dev.backend.dto.UserAddress;
import com.dev.backend.dto.Wallet;
import com.dev.backend.util.TransactionConstants;

/**
 * @author ashus
 *
 */
public class DatabaseDelegateImpl implements DatabaseDelegate {

	@Autowired
	MenuDao menuDao;
	
	@Autowired
	UserDao userDao;

	@Autowired
	OrderDao orderDao;
	
	@Autowired
	TransactionDao transactionDao;
	
	@Override
	@Transactional(propagation=Propagation.SUPPORTS,readOnly=true)
	public List<Menu> getTodaysMenu() {
		return menuDao.getTodaysMenu();
	}
	
	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public boolean createUser(User user) {
		return userDao.createUser(user);
	}
	
	@Override
	public boolean createAddress(UserAddress address) {
		return userDao.createAddress(address);
	}
	
	@Transactional(propagation=Propagation.REQUIRED)
	public boolean createReferralCashBack(User user) {
		User refereeUser = userDao.getUserByRefereeCode(user.getRefereeCode());
		try {
			Transaction transaction = new Transaction();
			transaction.setTransactionId(generateTransactionId(refereeUser));
			transaction.setWallet(refereeUser.getWallet());
			transaction.setAmount(TransactionConstants.REFERRAL_BONUS_AMOUNT);
			transaction.setTransactionType(TransactionType.CREDIT);
			transaction.setTransactionCategory(TransactionCategory.REFERRAL_BONUS);
			transaction.setComment("Referral Bonus for User:"+user.getName());
			transactionDao.createTransaction(transaction);
			return true;
		} catch (Exception e) {
			System.out.println("failed to create transaction");
			e.printStackTrace();
		}
		return false;
	}

	@Override
	@Transactional(propagation=Propagation.SUPPORTS,readOnly=true)
	public Wallet getUserWallet(String userId) {
		return userDao.getUserWallet(userId);
	}

	@Override
	@Transactional(propagation=Propagation.SUPPORTS,readOnly=true)
	public List<UserAddress> getUserAddress(String userId) {
		return userDao.getUserAddress(userId);
	}
	
	@Override
	@Transactional(propagation=Propagation.SUPPORTS,readOnly=true)
	public List<UserActivity> getUserActivity(String userId) {
		return userDao.getUserActivity(userId);
	}
	
	@Override
	@Transactional(propagation=Propagation.SUPPORTS,readOnly=true)
	public UserAddress getAddressById(String addressId) {
		return userDao.getAddressById(addressId);
	}

	@Override
	@Transactional(propagation=Propagation.SUPPORTS,readOnly=true)
	public Menu getMenuItemById(String menuItem) {
		return menuDao.getMenuById(menuItem);
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public boolean createOrder(Order order, Transaction transaction) {
		orderDao.createOrder(order);
		transactionDao.createTransaction(transaction);
		return true;
	}
	
	@Override
	@Transactional(propagation=Propagation.SUPPORTS,readOnly=true)
	public Order getOrder(String orderId) {
		return orderDao.getOrder(orderId);
	}

	@Override
	@Transactional(propagation=Propagation.SUPPORTS,readOnly=true)
	public List<Order> getOrdersByCustomer(String userId) {
		return orderDao.getOrdersByCustomer(userId);
	}
	
	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public String updateOrderStatus(String orderId, String status) {
		return orderDao.updateOrderStatus(orderId, status);
	}

	@Override
	@Transactional(propagation=Propagation.SUPPORTS,readOnly=true)
	public User getUser(String userId) {
		return userDao.getUser(userId);
	}

	@Override
	@Transactional(propagation=Propagation.SUPPORTS,readOnly=true)
	public List<Transaction> getTransactionsByWallet(String walletId) {
		return transactionDao.getTransactionsByWallet(walletId);
	}

	@Override
	@Transactional(propagation=Propagation.SUPPORTS,readOnly=true)
	public List<Transaction> getTransactionsByWallet(String walletId,
			TransactionType debit) {
		return transactionDao.getTransactionsByWallet(walletId,debit);
	}

	@Override
	@Transactional(propagation=Propagation.SUPPORTS,readOnly=true)
	public List<Transaction> getTransactionsByWallet(String walletId,
			TransactionCategory promotional) {
		return transactionDao.getTransactionsByWallet(walletId,promotional);
	}
	
	/**
	 * Generates transactionId based on currentTimestamp sandwiched in first and last digit of mobile number
	 * @param user
	 * @return
	 */
	public String generateTransactionId(User user) {
		StringBuffer sb = new StringBuffer();
		sb.append(user.getPhone().charAt(0));
		sb.append(Instant.now().toEpochMilli());
		sb.append(user.getPhone().charAt(9));
		return sb.toString();
	}

	@Override
	public Transaction createTransaction(Transaction transaction) {
		return null;
	}

	@Override
	public String updateTransaction(Transaction transaction) {
		return null;
	}
	
	public MenuDao getMenuDao() {
		return menuDao;
	}

	public void setMenuDao(MenuDao menuDao) {
		this.menuDao = menuDao;
	}

	public UserDao getUserDao() {
		return userDao;
	}

	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}
	
	public OrderDao getOrderDao() {
		return orderDao;
	}

	public void setOrderDao(OrderDao orderDao) {
		this.orderDao = orderDao;
	}

	public TransactionDao getTransactionDao() {
		return transactionDao;
	}

	public void setTransactionDao(TransactionDao transactionDao) {
		this.transactionDao = transactionDao;
	}

}
