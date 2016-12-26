package com.dev.backend.service.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dev.backend.delegate.DatabaseDelegate;
import com.dev.backend.dto.Menu;
import com.dev.backend.dto.User;
import com.dev.backend.dto.UserActivity;
import com.dev.backend.dto.UserAddress;
import com.dev.backend.dto.Wallet;

@Controller
public class TDKUserService {

	@Autowired
	private DatabaseDelegate delegate;

	@RequestMapping(value="/user/info",method=RequestMethod.GET,produces=MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody User getUser(String userId) {
		return delegate.getUser(userId);
	}

	@RequestMapping(value="/user/wallet",method=RequestMethod.GET,produces=MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Wallet getUserWallet(String userId) {
		return delegate.getUserWallet(userId);
	}

	@RequestMapping(value="/user/address",method=RequestMethod.GET,produces=MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody List<UserAddress> getUserAddress(String userId) {
		return delegate.getUserAddress(userId);
	}

	@RequestMapping(value="/user/activity",method=RequestMethod.GET,produces=MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody List<UserActivity> getUserActivity(String userId) {
		return delegate.getUserActivity(userId);
	}

	public DatabaseDelegate getDelegate() {
		return delegate;
	}

	public void setDelegate(DatabaseDelegate delegate) {
		this.delegate = delegate;
	}
}
