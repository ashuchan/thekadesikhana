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
		criteria.add(Restrictions.eq("phone", userId));
		return (User) criteria.uniqueResult();
	}

	@Override
	public Wallet getUserWallet(String userId) {
		System.out.println("Fetching wallet info for user id: "+ userId);
		Criteria criteria = getSession().createCriteria(User.class);
		criteria.add(Restrictions.eq("phone", userId));
		return ((User) criteria.uniqueResult()).getWallet();
	}

	@Override
	public List<UserAddress> getUserAddress(String userId) {
		System.out.println("Fetching Address for user id: "+ userId);
		Criteria criteria = getSession().createCriteria(UserAddress.class);
		criteria.add(Restrictions.eq("userId", userId));
		return criteria.list();
	}

	@Override
	public List <UserActivity> getUserActivity(String userId) {
		System.out.println("Fetching User Activity for user id: "+ userId);
		Criteria criteria = getSession().createCriteria(UserActivity.class);
		criteria.add(Restrictions.eq("userId", userId));
		return criteria.list();
	}

	@Override
	public Token getUserToken(String userId) {
		System.out.println("Fetching User Token for user id: "+ userId);
		Criteria criteria = getSession().createCriteria(Token.class);
		criteria.add(Restrictions.eq("userId", userId));
		return (Token) criteria.list();
	}

	@Override
	public void refreshTokens(String userId) {
		
	}
	
	@Override
	public boolean createUser(User user) {
		try{
			getSession().saveOrUpdate(user);
			return true;
		} catch( Exception e) {
			//TODO: Log
		}
		return false;
	}

	@Override
	public User getUserByRefereeCode(String refereeCode) {
		System.out.println("Fetching User with referralCode: "+ refereeCode);
		Criteria criteria = getSession().createCriteria(User.class);
		criteria.add(Restrictions.eq("referralCode", refereeCode));
		return (User) criteria.uniqueResult();
	}

	@Override
	public boolean createAddress(UserAddress address) {
		getSession().save(address);
		return true;
	}

	@Override
	public UserAddress getAddressById(String addressId) {
		System.out.println("Fetching UserAddress with addressId: "+ addressId);
		Criteria criteria = getSession().createCriteria(UserAddress.class);
		criteria.add(Restrictions.eq("addressId", addressId));
		return (UserAddress) criteria.uniqueResult();
	}

	@Override
	public void updateWallet(Wallet wallet) {
		getSession().update(wallet);
	}
}
