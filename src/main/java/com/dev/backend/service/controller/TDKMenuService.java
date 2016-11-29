package com.dev.backend.service.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dev.backend.dao.MenuDao;
import com.dev.backend.dto.Menu;

@Controller
public class TDKMenuService {
	
	@Autowired
	MenuDao menuDao;
	
	@RequestMapping(value="/menu/today",method=RequestMethod.GET,produces=MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody List<Menu> getTodaysMenu() {
		return menuDao.getTodaysMenu();
	}

	public MenuDao getMenuDao() {
		return menuDao;
	}

	public void setMenuDao(MenuDao menuDao) {
		this.menuDao = menuDao;
	}

}
