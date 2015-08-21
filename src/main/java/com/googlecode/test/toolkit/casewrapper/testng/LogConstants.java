package com.googlecode.test.toolkit.casewrapper.testng;

public final class LogConstants {

	private LogConstants() {
		// no instance
	}

	static final String LOG_FORMAT_SPLIT_STRING = "|||||||||||||||||||||||||||||||";
	static final String LOG_FORMAT_FOR_WITHOUT_CONSUMED_TIME = "\n"
			+ LOG_FORMAT_SPLIT_STRING + "%s:%s" + LOG_FORMAT_SPLIT_STRING + "\n";
	static final String LOG_FORMAT_FOR_CONSUMED_TIME = "\n" + LOG_FORMAT_SPLIT_STRING
			+ "%s:%s:consumed %dms" + LOG_FORMAT_SPLIT_STRING + "\n";
	static final String LOG_FORMAT_FOR_TEST_START = "\n" + LOG_FORMAT_SPLIT_STRING
			+ "Test Start at %s" + LOG_FORMAT_SPLIT_STRING + "\n";
	static final String LOG_FORMAT_FOR_TEST_FINISH = "\n" + LOG_FORMAT_SPLIT_STRING
			+ "Test Finish at %s consumed %d(s)" + LOG_FORMAT_SPLIT_STRING + "\n";

}
