package com.dev.backend.dao;

import java.sql.Date;
import java.util.List;

import com.dev.backend.dto.Menu;

public interface MenuDao {

	public List<Menu> getMenuByDate(Date date);
	
	public List<Menu> getTodaysMenu();
	
	public Menu getTodaysMenuByCuisine(int cusisine);

	public Menu getMenuById(String menuItem);
}
