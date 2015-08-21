package com.googlecode.test.toolkit.database.exception;

public class DbConfigException extends DbException {

	private static final long serialVersionUID = 1L;

	public DbConfigException() {
	}

	public DbConfigException(String message) {
		super(message);
	}

	public DbConfigException(Throwable cause) {
		super(cause);
	}

	public DbConfigException(String message, Throwable cause) {
		super(message, cause);
	}
  
}
