package com.googlecode.test.toolkit.server.common.exception;

/**
 * @author fu.jian
 * date Jul 25, 2012
 */
public class UncheckedServerOperationException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public UncheckedServerOperationException() {
	}

	public UncheckedServerOperationException(String message) {
		super(message);
	}

	public UncheckedServerOperationException(Throwable cause) {
		super(cause);
	}

	public UncheckedServerOperationException(String message, Throwable cause) {
		super(message, cause);
	}

}
