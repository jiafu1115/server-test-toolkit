package com.googlecode.test.toolkit.job.exception;

public class JobExecuteException extends JobException {

	private static final long serialVersionUID = -1;

	public JobExecuteException() {
 	}

	public JobExecuteException(String message) {
		super(message);
 	}

	public JobExecuteException(Throwable cause) {
		super(cause);
 	}

	public JobExecuteException(String message, Throwable cause) {
		super(message, cause);
 	}

}
