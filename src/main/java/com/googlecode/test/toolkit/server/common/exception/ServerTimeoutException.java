package com.googlecode.test.toolkit.server.common.exception;

/**
 * @author fu.jian
 * date Jul 26, 2012
 */
public class ServerTimeoutException extends UncheckedServerOperationException {

 	private static final long serialVersionUID = 1L;

	public ServerTimeoutException() {
 	}

	public ServerTimeoutException(String message) {
		super(message);
 	}

	public ServerTimeoutException(Throwable cause) {
		super(cause);
 	}

	public ServerTimeoutException(String message, Throwable cause) {
		super(message, cause);
 	}

}
