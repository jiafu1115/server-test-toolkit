package com.googlecode.test.toolkit.file;

public final class RandomFileException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public RandomFileException() {
 	}

	public RandomFileException(String message) {
		super(message);
 	}

	public RandomFileException(Throwable cause) {
		super(cause);
 	}

	public RandomFileException(String message, Throwable cause) {
		super(message, cause);
 	}

}
