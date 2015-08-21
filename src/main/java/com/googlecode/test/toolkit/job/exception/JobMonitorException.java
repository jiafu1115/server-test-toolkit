package com.googlecode.test.toolkit.job.exception;

public class JobMonitorException extends JobException {

	private static final long serialVersionUID = -1;

	public JobMonitorException() {
 	}

	public JobMonitorException(String message) {
		super(message);
 	}

	public JobMonitorException(Throwable cause) {
		super(cause);
 	}

	public JobMonitorException(String message, Throwable cause) {
		super(message, cause);
 	}

}
