package com.dev.backend.dao.impl;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;

import com.dev.backend.dao.AbstractDao;
import com.dev.backend.dao.MenuDao;
import com.dev.backend.dto.Menu;

public class MenuDaoImpl extends AbstractDao implements MenuDao{
	
	@Override
	public List<Menu> getMenuByDate(Date date) {
		System.out.println("Fetching menu for "+ date);
		Criteria criteria = getSession().createCriteria(Menu.class);
		criteria.add(Restrictions.eq("date", date));
		return criteria.list();
	}

	@Override
	public List<Menu> getTodaysMenu() {
		List<Menu> todayMenu = getMenuByDate(new Date(System.currentTimeMillis()));
		if(todayMenu.size()>0 )
			return todayMenu;
		return getMenuByDate(Date.valueOf("2016-12-18"));
	}

	@Override
	public Menu getTodaysMenuByCuisine(int cuisine) {
		List<Menu> todaysMenu = getTodaysMenu();
		Optional<Menu> menu = todaysMenu.stream()
				.filter(m -> m.getCuisine().getId() == cuisine).findAny();
		return menu.get();
	}

	@Override
	public Menu getMenuById(String menuItem) {
		System.out.println("Fetching menu for "+ menuItem);
		Criteria criteria = getSession().createCriteria(Menu.class);
		criteria.add(Restrictions.eq("id", Integer.parseInt(menuItem)));
		return (Menu)criteria.uniqueResult();
	}

}
