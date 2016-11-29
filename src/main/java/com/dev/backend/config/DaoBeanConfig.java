package com.dev.backend.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.dev.backend.dao.MenuDao;
import com.dev.backend.dao.impl.MenuDaoImpl;

@Configuration
public class DaoBeanConfig {
	
	@Bean
	public MenuDao getMenuDao(){
		return new MenuDaoImpl();
	}
	
}
