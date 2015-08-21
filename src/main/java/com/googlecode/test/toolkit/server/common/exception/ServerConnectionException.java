package com.googlecode.test.toolkit.server.common.exception;

/**
 * @author fu.jian
 * date Jul 26, 2012
 */
public class ServerConnectionException extends UncheckedServerOperationException {
 
	private static final long serialVersionUID = -1;

	public ServerConnectionException() {
 	}

	public ServerConnectionException(String message) {
		super(message);
 	}

	public ServerConnectionException(Throwable cause) {
		super(cause);
 	}

	public ServerConnectionException(String message, Throwable cause) {
		super(message, cause);
 	}

}
