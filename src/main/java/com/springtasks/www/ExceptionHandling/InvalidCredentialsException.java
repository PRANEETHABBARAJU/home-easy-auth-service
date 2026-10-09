package com.springtasks.www.ExceptionHandling;

public class InvalidCredentialsException extends RuntimeException {
	public InvalidCredentialsException(String message) {
		super(message);
	}

}
