package com.dev.backend.dao;

import java.util.List;

import com.dev.backend.dto.Transaction;
import com.dev.backend.dto.Transaction.TransactionCategory;
import com.dev.backend.dto.Transaction.TransactionType;

public interface TransactionDao {

	List<Transaction> getTransactionsByWallet(String walletId);

	List<Transaction> getTransactionsByWallet(String walletId,
			TransactionType debit);

	List<Transaction> getTransactionsByWallet(String walletId,
			TransactionCategory promotional);

	void createTransaction(Transaction transaction);

}
