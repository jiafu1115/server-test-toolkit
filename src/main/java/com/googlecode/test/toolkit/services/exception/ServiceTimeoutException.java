package com.googlecode.test.toolkit.services.exception;

public class ServiceTimeoutException extends ServiceException {

	private static final long serialVersionUID = 1L;

	public ServiceTimeoutException() {
 	}

	public ServiceTimeoutException(String arg0) {
		super(arg0);
 	}

	public ServiceTimeoutException(Throwable arg0) {
		super(arg0);
 	}

	public ServiceTimeoutException(String arg0, Throwable arg1) {
		super(arg0, arg1);
 	}

}
