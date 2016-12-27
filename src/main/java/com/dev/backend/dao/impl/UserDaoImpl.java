package com.dev.backend.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;

import com.dev.backend.dao.AbstractDao;
import com.dev.backend.dao.UserDao;
import com.dev.backend.dto.Token;
import com.dev.backend.dto.User;
import com.dev.backend.dto.UserActivity;
import com.dev.backend.dto.UserAddress;
import com.dev.backend.dto.Wallet;

public class UserDaoImpl extends AbstractDao implements UserDao{

	@Override
	public User getUser(String userId) {
		System.out.println("Fetching user for id: "+ userId);
		Criteria criteria = getSession().createCriteria(User.class);
		criteria.add(Restrictions.eq("user_id", userId));
		return (User) criteria.list();
	}

	@Override
	public Wallet getUserWallet(String userId) {
		System.out.println("Fetching wallet info for user id: "+ userId);
		Criteria criteria = getSession().createCriteria(Wallet.class);
		criteria.add(Restrictions.eq("user_id", userId));
		return (Wallet) criteria.list();
	}

	@Override
	public List <UserAddress> getUserAddress(String userId) {
		System.out.println("Fetching Address for user id: "+ userId);
		Criteria criteria = getSession().createCriteria(UserAddress.class);
		criteria.add(Restrictions.eq("user_id", userId));
		return criteria.list();
	}

	@Override
	public List <UserActivity> getUserActivity(String userId) {
		System.out.println("Fetching User Activity for user id: "+ userId);
		Criteria criteria = getSession().createCriteria(UserActivity.class);
		criteria.add(Restrictions.eq("user_id", userId));
		return criteria.list();
	}

	@Override
	public Token getUserToken(String userId) {
		System.out.println("Fetching User Token for user id: "+ userId);
		Criteria criteria = getSession().createCriteria(Token.class);
		criteria.add(Restrictions.eq("user_id", userId));
		return (Token) criteria.list();
	}

	@Override
	public void refreshTokens(String userId) {
		
	}

	@Override
	public boolean createUser(User user) {
		try{
			getSession().save(user);
			getSession().save(user.getAccessTokens());
			return createUserWallet(user);
		} catch( Exception e) {
			//TODO: Log
		}
		return false;
	}


	private boolean createUserWallet(User user){
		Wallet wallet = new Wallet();
		wallet.setWalletId(user.getMobileNumber());
		wallet.setUserId(user.getMobileNumber());
		wallet.setPromotionalBalance(0);
		wallet.setWalletBalance(0);
		try{
			getSession().save(wallet);
			return true;
		} catch( Exception e) {
			//TODO: Log
		}
		return false;
	}
}
