package com.dev.backend.delegate;

import java.util.List;

import com.dev.backend.dto.Menu;

public interface DatabaseDelegate {
	
	public List<Menu> getTodaysMenu();

}
