package com.googlecode.test.toolkit.job.exception;

public class JobException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public JobException() {
	}

	public JobException(String message) {
		super(message);
	}

	public JobException(Throwable cause) {
		super(cause);
	}

	public JobException(String message, Throwable cause) {
		super(message, cause);
	}

}
