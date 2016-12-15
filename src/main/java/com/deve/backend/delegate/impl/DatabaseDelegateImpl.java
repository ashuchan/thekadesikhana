package com.deve.backend.delegate.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.dev.backend.dao.MenuDao;
import com.dev.backend.delegate.DatabaseDelegate;
import com.dev.backend.dto.Menu;

public class DatabaseDelegateImpl implements DatabaseDelegate {

	@Autowired
	MenuDao menuDao;

	@Override
	@Transactional(propagation=Propagation.SUPPORTS,readOnly=true)
	public List<Menu> getTodaysMenu() {
		return menuDao.getTodaysMenu();
	}

	public MenuDao getMenuDao() {
		return menuDao;
	}

	public void setMenuDao(MenuDao menuDao) {
		this.menuDao = menuDao;
	}
	
	
}
