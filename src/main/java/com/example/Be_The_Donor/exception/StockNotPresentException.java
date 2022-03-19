package com.example.Be_The_Donor.exception;

public class StockNotPresentException extends RuntimeException {
	private static final long serialVersionUID = 1L;
	public StockNotPresentException(String message) {
		super(message);
	}

}

