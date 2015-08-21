package com.googlecode.test.toolkit.database.exception;

public class DbConnectionException extends DbException {

	private static final long serialVersionUID = -1L;

	public DbConnectionException() {
	}

	public DbConnectionException(String message) {
		super(message);
	}

	public DbConnectionException(Throwable cause) {
		super(cause);
	}

	public DbConnectionException(String message, Throwable cause) {
		super(message, cause);
	}
 
}
