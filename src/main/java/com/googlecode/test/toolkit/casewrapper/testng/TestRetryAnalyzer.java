package com.googlecode.test.toolkit.casewrapper.testng;

import org.apache.log4j.Logger;
import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class TestRetryAnalyzer implements IRetryAnalyzer {

	private static final Logger LOGGER = Logger
			.getLogger(TestRetryAnalyzer.class);
 
	private boolean needRetry = true;

	public synchronized boolean retry(ITestResult result) {
		if (needRetry) {
			LOGGER.info(String.format(LogConstants.LOG_FORMAT_FOR_WITHOUT_CONSUMED_TIME,
					getTestMethodName(result), "[retry]"));
 			needRetry = false;
			return true;
		}
		return false;
	}

	private String getTestMethodName(ITestResult iTestResult) {
		return iTestResult.getTestClass().getRealClass().getName() + "."
				+ iTestResult.getMethod().getMethodName();
	}

	public boolean isNeedRetry() {
		return needRetry;
	}

	public void setNeedRetry(boolean needRetry) {
		this.needRetry = needRetry;
	}
	
	
}