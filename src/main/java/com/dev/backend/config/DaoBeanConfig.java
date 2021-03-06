package com.dev.backend.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.dev.backend.dao.MenuDao;
import com.dev.backend.dao.OrderDao;
import com.dev.backend.dao.TransactionDao;
import com.dev.backend.dao.UserDao;
import com.dev.backend.dao.impl.MenuDaoImpl;
import com.dev.backend.dao.impl.OrderDaoImpl;
import com.dev.backend.dao.impl.TransactionDaoImpl;
import com.dev.backend.dao.impl.UserDaoImpl;
import com.dev.backend.delegate.DatabaseDelegate;
import com.deve.backend.delegate.impl.DatabaseDelegateImpl;

@Configuration
public class DaoBeanConfig {
	
	@Bean
	public MenuDao getMenuDao(){
		return new MenuDaoImpl();
	}
	
	@Bean
	public UserDao getUserDao() {
		return new UserDaoImpl();
	}
	
	@Bean
	public DatabaseDelegate getDatabaseDelegate() {
		return new DatabaseDelegateImpl();
	}
	
	@Bean
	public OrderDao getOrderDao() {
		return new OrderDaoImpl();
	}
	
	@Bean
	public TransactionDao getTransactionDao() {
		return new TransactionDaoImpl();
	}
	
}
