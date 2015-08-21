package com.googlecode.test.toolkit.casewrapper.testng;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.Set;

import org.testng.IAnnotationTransformer;
import org.testng.IResultMap;
import org.testng.IRetryAnalyzer;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestNGMethod;
import org.testng.ITestResult;
import org.testng.annotations.ITestAnnotation;

public class TestSkippedResultUpdateListener implements IAnnotationTransformer, ITestListener {

	@SuppressWarnings("rawtypes")
	@Override
	public void transform(ITestAnnotation annotation, Class clazz,
			Constructor constructor, Method method) {
		IRetryAnalyzer retry = annotation.getRetryAnalyzer();
		if (retry == null) {
			annotation.setRetryAnalyzer(TestRetryAnalyzer.class);
		}
	}

	@Override
	public void onTestStart(ITestResult paramITestResult) {

	}

	@Override
	public void onTestSuccess(ITestResult paramITestResult) {

	}

	@Override
	public void onTestFailure(ITestResult paramITestResult) {

	}

	@Override
	public void onTestSkipped(ITestResult paramITestResult) {

	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(
			ITestResult paramITestResult) {

	}

	@Override
	public void onStart(ITestContext paramITestContext) {

	}

	@Override
	public void onFinish(ITestContext paramITestContext) {
	    IResultMap skippedTests = paramITestContext.getSkippedTests();
	    Set<ITestResult> allSkippedResults = skippedTests.getAllResults();
	    for (ITestResult iTestResult : allSkippedResults) {
 	        ITestNGMethod method = iTestResult.getMethod();
            if(!method.isTest()){
	            paramITestContext.getFailedTests().addResult(iTestResult, method);
 	        }
        }

	    skippedTests.getAllResults().clear();
	}
}