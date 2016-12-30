package com.deve.backend.delegate.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.dev.backend.dao.MenuDao;
import com.dev.backend.dao.UserDao;
import com.dev.backend.delegate.DatabaseDelegate;
import com.dev.backend.dto.Menu;
import com.dev.backend.dto.User;
import com.dev.backend.dto.UserActivity;
import com.dev.backend.dto.UserAddress;
import com.dev.backend.dto.Wallet;

public class DatabaseDelegateImpl implements DatabaseDelegate {

	@Autowired
	MenuDao menuDao;
	
	@Autowired
	UserDao userDao;

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

	@Override
	@Transactional(propagation=Propagation.SUPPORTS,readOnly=true)
	public User getUser(String userId) {
		return userDao.getUser(userId);
	}
	
}
