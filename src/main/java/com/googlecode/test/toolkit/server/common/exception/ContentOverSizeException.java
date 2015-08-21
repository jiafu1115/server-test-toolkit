package com.googlecode.test.toolkit.server.common.exception;

 
/**
 * some content should be loaded into jvm. So to avoid out of memory use this exception to check it. 
 * @author fu.jian
 * date Aug 17, 2012
 */
public class ContentOverSizeException extends UncheckedServerOperationException {

	private static final long serialVersionUID = 1L;

	public ContentOverSizeException() {
 	}

	public ContentOverSizeException(String message) {
		super(message);
 	}

	public ContentOverSizeException(Throwable cause) {
		super(cause);
 	}

	public ContentOverSizeException(String message, Throwable cause) {
		super(message, cause);
 	}

}
