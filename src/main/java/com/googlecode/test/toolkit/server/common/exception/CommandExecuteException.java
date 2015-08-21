package com.googlecode.test.toolkit.server.common.exception;

/**
 * @author fu.jian
 * date Jul 26, 2012
 */
public class CommandExecuteException extends UncheckedServerOperationException {
 
	private static final long serialVersionUID = -1;

	public CommandExecuteException() {
 	}

	public CommandExecuteException(String message) {
		super(message);
 	}

	public CommandExecuteException(Throwable cause) {
		super(cause);
 	}

	public CommandExecuteException(String message, Throwable cause) {
		super(message, cause);
 	}

}
