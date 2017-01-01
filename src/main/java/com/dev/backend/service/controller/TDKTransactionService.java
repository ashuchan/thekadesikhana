package com.dev.backend.service.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dev.backend.delegate.DatabaseDelegate;
import com.dev.backend.dto.Transaction;
import com.dev.backend.dto.Transaction.TransactionCategory;
import com.dev.backend.dto.Transaction.TransactionType;

@Controller
public class TDKTransactionService {

	@Autowired
	private DatabaseDelegate delegate;
	
	@RequestMapping(value="/transaction/{walletId}",method=RequestMethod.GET,produces=MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody List<Transaction> getTransactionsByWallet(@PathVariable String walletId) {
		//TODO: Log
		return delegate.getTransactionsByWallet(walletId);
	}
	
	@RequestMapping(value="/transaction/debit/{walletId}",method=RequestMethod.GET,produces=MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody List<Transaction> getDebitByWallet(String walletId) {
		
		return delegate.getTransactionsByWallet(walletId, TransactionType.DEBIT);
	}
	
	@RequestMapping(value="/transaction/credit/{walletId}",method=RequestMethod.GET,produces=MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody List<Transaction> getCreditByWallet(String walletId) {
		
		return delegate.getTransactionsByWallet(walletId, TransactionType.CREDIT);
	}
	
	@RequestMapping(value="/transaction/promotional/{walletId}",method=RequestMethod.GET,produces=MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody List<Transaction> getPromoTransactionsByWallet(String walletId) {
		
		return delegate.getTransactionsByWallet(walletId, TransactionCategory.PROMOTIONAL);
	}
	
	@RequestMapping(value="/transaction",method=RequestMethod.POST,produces=MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Transaction createTransactionForOrder(Transaction transaction) {
		/*
		 * Transaction that gets created on order creation
		 * Order details are coming as input.
		 * Get userId, addressId, list of MenuItems and other details from the request.
		 * Generate OrderId and TransactionId.
		 * Transaction with type as debit and category from the WALLET/GATEWAY
		 * Check for referrel cashbacks. CHAIN_ORDER_BONUS
		 */
		
		
		return delegate.createTransaction(transaction);
	}
	
	@RequestMapping(value="/transaction",method=RequestMethod.PUT,produces=MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody String updateTransaction(Transaction transaction) {
		
		return delegate.updateTransaction(transaction);
	}

	public DatabaseDelegate getDelegate() {
		return delegate;
	}

	public void setDelegate(DatabaseDelegate delegate) {
		this.delegate = delegate;
	}
	
}
