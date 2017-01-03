package com.dev.backend.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;

import com.dev.backend.dao.AbstractDao;
import com.dev.backend.dao.TransactionDao;
import com.dev.backend.dto.Transaction;
import com.dev.backend.dto.Transaction.TransactionCategory;
import com.dev.backend.dto.Transaction.TransactionType;

public class TransactionDaoImpl extends AbstractDao implements TransactionDao {

	@Override
	public List<Transaction> getTransactionsByWallet(String walletId) {
		System.out.println("Fetching transactions for wallet: "+ walletId);
		Criteria criteria = getSession().createCriteria(Transaction.class);
		criteria.add(Restrictions.eq("phone", walletId));
		return criteria.list();
	}

	@Override
	public List<Transaction> getTransactionsByWallet(String walletId,
			TransactionType debit) {
		System.out.println("Fetching transactions for wallet: "+ walletId +" and for type:" + debit);
		Criteria criteria = getSession().createCriteria(Transaction.class);
		criteria.add(Restrictions.eq("phone", walletId));
		criteria.add(Restrictions.eq("type", debit.name()));
		return criteria.list();
	}

	@Override
	public List<Transaction> getTransactionsByWallet(String walletId,
			TransactionCategory promotional) {
		System.out.println("Fetching transactions for wallet: "+ walletId +" and for type:" + promotional);
		Criteria criteria = getSession().createCriteria(Transaction.class);
		criteria.add(Restrictions.eq("phone", walletId));
		criteria.add(Restrictions.eq("type", promotional.name()));
		return criteria.list();
	}

	@Override
	public void createTransaction(Transaction transaction) {

		System.out.println("Saving transactions for wallet: "
				+ transaction.getWallet()
				+ " and for type:" + transaction.getTransactionCategory());
		getSession().save(transaction);
	}
	
	

}
