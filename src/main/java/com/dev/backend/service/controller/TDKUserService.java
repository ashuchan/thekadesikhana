package com.dev.backend.service.controller;

import java.util.List;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dev.backend.dto.Feedback;
import com.dev.backend.dto.User;
import com.dev.backend.dto.UserActivity;
import com.dev.backend.dto.UserAddress;
import com.dev.backend.dto.Wallet;
import com.dev.backend.to.ResponseObject;
import com.dev.backend.util.UserUtil;

@Controller
public class TDKUserService extends TDKServices {

	@RequestMapping(value="/user/{userId}",method=RequestMethod.GET,produces=MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody User getUser(@PathVariable String userId) {
		return delegate.getUser(userId);
	}
	
	@RequestMapping(value="/user",method=RequestMethod.GET,produces=MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody User getUserParam(@RequestParam String userId) {
		return delegate.getUser(userId);
	}


	@RequestMapping(value="/user/wallet/{userId}",method=RequestMethod.GET,produces=MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Wallet getUserWallet(@PathVariable String userId) {
		return delegate.getUserWallet(userId);  
	}
	
	@RequestMapping(value="/user/wallet",method=RequestMethod.GET,produces=MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Wallet getUserWalletParam(@RequestParam String userId) {
		return delegate.getUserWallet(userId);  
	}

	@RequestMapping(value="/user/address/{userId}",method=RequestMethod.GET,produces=MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseObject getUserAddress(@PathVariable String userId) {
		ResponseObject object = new ResponseObject();
		try {
			object.setData(delegate.getUserAddress(userId));
			object.setMessage("Fetched address of User successfully");
			object.setSuccess(true);
		} catch (Exception e) {
			object.setMessage("Failed to fetch User Address from database");
		}
		return object;
	}

	@RequestMapping(value="/user/address",method=RequestMethod.GET,produces=MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseObject getUserAddressParam(@RequestParam String userId) {
		return getUserAddress(userId);
	}
	
	@RequestMapping(value="/user/activity/{userId}",method=RequestMethod.GET,produces=MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody List<UserActivity> getUserActivity(@PathVariable String userId) {
		return delegate.getUserActivity(userId);
	}
	
	@RequestMapping(value="/user/address",method=RequestMethod.POST,produces=MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody UserAddress saveAddress(@RequestBody UserAddress address) {
		if(delegate.createAddress(address)) {
			return address;
		}
		return null;
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
	
	@RequestMapping(value="/feedback", method=RequestMethod.POST,consumes=MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseObject saveFeedback(@RequestBody Feedback feedback) {
		ResponseObject object = new ResponseObject();
		delegate.saveFeedback(feedback);
		object.setMessage("Feedback saved successfully");
		object.setData(feedback);
		object.setSuccess(true);
		return object;
	}

}
