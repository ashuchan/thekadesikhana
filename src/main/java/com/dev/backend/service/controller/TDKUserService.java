package com.dev.backend.service.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.dev.backend.dto.User;
import com.dev.backend.dto.UserActivity;
import com.dev.backend.dto.UserAddress;
import com.dev.backend.dto.Wallet;
import com.dev.backend.util.UserUtil;

@Controller
public class TDKUserService extends TDKServices {

	@RequestMapping(value="/user/{userId}",method=RequestMethod.GET,produces=MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody User getUser(@PathVariable String userId) {
		return delegate.getUser(userId);
	}

	@RequestMapping(value="/user/wallet/{userId}",method=RequestMethod.GET,produces=MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Wallet getUserWallet(String userId) {
		return delegate.getUserWallet(userId);
	}

	@RequestMapping(value="/user/address/{userId}",method=RequestMethod.GET,produces=MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody List<UserAddress> getUserAddress(@PathVariable String userId) {
		return delegate.getUserAddress(userId);
	}

	@RequestMapping(value="/user/activity",method=RequestMethod.GET,produces=MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody List<UserActivity> getUserActivity(String userId) {
		return delegate.getUserActivity(userId);
	}
	
	@RequestMapping(value="/user/address",method=RequestMethod.POST,produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(code=HttpStatus.CREATED)
	public String saveAddress(UserAddress address) {
		if(delegate.createAddress(address)) {
			return "SUCCESS";
		}
		return "FAILURE";
	}
	
	@RequestMapping(value="/user", method=RequestMethod.POST,consumes=MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody User saveUser(@RequestBody User user) {
		/*
		 * Generate new UserId
		 * Generate ReferralCode
		 * Generate Tokens
		 * Update Database
		 * Referral Transaction
		 */
		UserUtil.generateUserAccessInfo(user);
		if(delegate.createUser(user)) {
			/*
			 * Return Success created Message
			 */
		}
		if(!delegate.createReferralCashBack(user)) {
			//Log message
		}
		return user;
	}

}
