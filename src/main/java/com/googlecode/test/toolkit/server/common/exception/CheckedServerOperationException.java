package com.googlecode.test.toolkit.server.common.exception;

/**
 * @author fu.jian
 * date Jul 26, 2012
 */
public class CheckedServerOperationException extends Exception {
 
	private static final long serialVersionUID = -1;

	public CheckedServerOperationException() {
 	}

	public CheckedServerOperationException(String message) {
		super(message);
 	}

	public CheckedServerOperationException(Throwable cause) {
		super(cause);
 	}

	public CheckedServerOperationException(String message, Throwable cause) {
		super(message, cause);
 	}

}
