package com.googlecode.test.toolkit.casewrapper.testng;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.testng.IAnnotationTransformer;
import org.testng.IRetryAnalyzer;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.annotations.ITestAnnotation;

public class TestRetryListener implements IAnnotationTransformer, ITestListener {

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
		Set<ITestResult> allResults = new HashSet<ITestResult>();
		allResults.addAll(paramITestContext.getPassedTests().getAllResults());
		allResults.addAll(paramITestContext.getFailedTests().getAllResults());
		allResults.addAll(paramITestContext.getSkippedTests().getAllResults());

		Map<Method, ArrayList<ITestResult>> allITestResultMap = new HashMap<Method, ArrayList<ITestResult>>();
		for (ITestResult iTestResult : allResults) {
			@SuppressWarnings("deprecation")
			Method method = iTestResult.getMethod().getMethod();
			if (allITestResultMap.get(method) == null) {
				allITestResultMap.put(method, new ArrayList<ITestResult>());
				allITestResultMap.get(method).add(iTestResult);

			} else {
				allITestResultMap.get(method).add(iTestResult);
			}
		}

		Collection<ArrayList<ITestResult>> resultMapValues = allITestResultMap
				.values();
		ArrayList<ITestResult> resultArrayList = new ArrayList<ITestResult>();

		for (ArrayList<ITestResult> arrayList : resultMapValues) {
			if (arrayList.size() > 1) {
				Comparator<ITestResult> c = new Comparator<ITestResult>() {

					@Override
					public int compare(ITestResult o1, ITestResult o2) {
						return (int) (o1.getStartMillis() - o2.getStartMillis());
					}
				};
				Collections.sort(arrayList, c);
				arrayList.remove(arrayList.size() - 1);
				resultArrayList.addAll(arrayList);
			}

		}

		for (ITestResult iTestResult : resultArrayList) {
			paramITestContext.getPassedTests().removeResult(iTestResult);
			paramITestContext.getFailedTests().removeResult(iTestResult);
			paramITestContext.getSkippedTests().removeResult(iTestResult);
			paramITestContext.getPassedConfigurations().removeResult(iTestResult);
			paramITestContext.getFailedConfigurations().removeResult(iTestResult);
			paramITestContext.getSkippedConfigurations().removeResult(iTestResult);
		}

	}
}