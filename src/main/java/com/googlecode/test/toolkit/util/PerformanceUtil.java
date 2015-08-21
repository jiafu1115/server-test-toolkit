package com.googlecode.test.toolkit.util;

import java.util.concurrent.TimeUnit;

public class PerformanceUtil {

	private static final String PERFORMANCE_COST_FORMAT = "[performance][cost][in milliseconds:{%d}][in %s:{%d}][%s]";

	private long startTime;
	private TimeUnit timeUnit;

	public static PerformanceUtil getInstance(TimeUnit timeUnit) {
		return new PerformanceUtil(timeUnit);
	}

	public static PerformanceUtil getInstance() {
		return new PerformanceUtil(TimeUnit.MILLISECONDS);
	}

	private PerformanceUtil(TimeUnit timeUnit) {
		this.timeUnit = timeUnit;
	}

	public void start() {
		startTime = System.currentTimeMillis();
	}

	public String end(String opertionMsg) {
		long delta = System.currentTimeMillis() - startTime;
		String unit = timeUnit.toString().toLowerCase();
		long convertedDelta = timeUnit.convert(delta, TimeUnit.MILLISECONDS);
		String printContent = String.format(PERFORMANCE_COST_FORMAT, delta, unit, convertedDelta,
				opertionMsg);

		return printContent;
	}
}