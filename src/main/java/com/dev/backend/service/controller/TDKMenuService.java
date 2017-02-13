package com.dev.backend.service.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dev.backend.delegate.DatabaseDelegate;
import com.dev.backend.dto.Menu;
import com.dev.backend.to.MenuTO;

@Controller
public class TDKMenuService extends TDKServices{
	
	@RequestMapping(value="/menu/today",method=RequestMethod.GET,produces=MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Map<String,List<Map<String, MenuTO>>> getTodaysMenu() {
		List<Menu> menuItems = delegate.getTodaysMenu();
		Map<String, MenuTO> menuMap = new HashMap<String, MenuTO>();
		menuItems.forEach(m->{
			String name = m.getCuisine().getName();
			if(menuMap.containsKey(name)){
				menuMap.get(name).getMenuItems().add(m);
			} else {
				MenuTO temp = new MenuTO();
				temp.setName(name);
				temp.getMenuItems().add(m);
				menuMap.put(name, temp);
			}
		});
		Map<String,List<Map<String, MenuTO>>> returnObject = new HashMap<>();
		List<Map<String, MenuTO>> list = new ArrayList<Map<String,MenuTO>>();
		menuMap.keySet().forEach(m->{
			Map<String, MenuTO> mm = new HashMap<String, MenuTO>();
			mm.put(m, menuMap.get(m));
			list.add(mm);
		});
		returnObject.put("FoodType", list);
		return returnObject;
	}

}
