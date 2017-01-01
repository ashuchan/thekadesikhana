package com.dev.backend.service.controller;

import org.springframework.beans.factory.annotation.Autowired;

import com.dev.backend.delegate.DatabaseDelegate;

public abstract class TDKServices {

	@Autowired
	protected DatabaseDelegate delegate;
	
	public DatabaseDelegate getDelegate() {
		return delegate;
	}

	public void setDelegate(DatabaseDelegate delegate) {
		this.delegate = delegate;
	}
}
