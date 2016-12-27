package com.dev.backend.util;

import java.sql.Timestamp;
import java.util.Date;
import java.util.UUID;

import com.dev.backend.dto.Token;
import com.dev.backend.dto.User;

public class UserUtil {

	private static void generateReferralCode(User user) {
		// TODO Auto-generated method stub
		/*
		 * Using last digit of mobilenumber +
		 * first 3 letters of email +
		 * alphabet corresponding to day and month of birth date
		 * last two digits of currenttimestamp
		 */
		String code = "";
		code += user.getMobileNumber().charAt(user.getMobileNumber().length()-1);
		code += user.getEmail().substring(0, 2);
		Date dob = user.getDob();
		
		code += 'a' + dob.getDay() % 26 - 1;
		code += 'a' + dob.getMonth() % 26 - 1;
		
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		
		code +=  System.currentTimeMillis() % 100;
		//TODO: Log
		System.out.println("System Generated Referal Code: " + code + " for User: " + user.getName() + " UserId: " + user.getMobileNumber());
		user.setReferralCode(code);
	}

	public static void generateUserAccessInfo(User user) {
		// TODO Auto-generated method stub
		generateReferralCode(user);
		generateTokens(user);
	}

	private static void generateTokens(User user) {
		// TODO Auto-generated method stub
		/*
		 * Random UUID's
		 * Set expiry time to 1 hour for accessToken and 15 days for refresh token
		 */
		Token token = new Token();
		token.setUser(user);
		token.setAcessToken(UUID.randomUUID().toString());
		token.setAccessTokenExpiry(60 * 60 * 1000);
		
		token.setRefreshToken(UUID.randomUUID().toString());
		token.setRefreshTokenExpiry(15 * 24 * 50 * 60 * 1000);
		
		user.setAccessTokens(token);
	}
	
	

}
