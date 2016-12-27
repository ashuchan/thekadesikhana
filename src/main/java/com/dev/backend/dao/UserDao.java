package com.dev.backend.dao;

import java.util.List;

import com.dev.backend.dto.Token;
import com.dev.backend.dto.User;
import com.dev.backend.dto.UserActivity;
import com.dev.backend.dto.UserAddress;
import com.dev.backend.dto.Wallet;

public interface UserDao {
	public User getUser(String userId);
	
	public Wallet getUserWallet(String userId);
	
	public List <UserAddress> getUserAddress(String userId);
	
	public List <UserActivity> getUserActivity(String userId);
	
	public Token getUserToken(String userId);
	
	public void refreshTokens(String userId);

	public boolean createUser(User user);
}
