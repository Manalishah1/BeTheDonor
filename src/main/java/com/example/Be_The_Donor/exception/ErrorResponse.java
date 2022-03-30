package com.example.Be_The_Donor.exception;

public class ErrorResponse {
	String message;
	Integer code;

	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public Integer getCode() {
		return code;
	}
	public void setCode(Integer code) {
		this.code = code;
	}
}
