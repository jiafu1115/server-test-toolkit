package com.googlecode.test.toolkit.util;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;

public class SleepUtil {

	public final static Map<String, TimeUnit> CONVERT_MAP_FOR_TIMEUNIT = new HashMap<String, TimeUnit>();
	private final static Logger LOGGER = Logger.getLogger(SleepUtil.class);

	static {
		CONVERT_MAP_FOR_TIMEUNIT.put("d", TimeUnit.DAYS);
		CONVERT_MAP_FOR_TIMEUNIT.put("h", TimeUnit.HOURS);
		CONVERT_MAP_FOR_TIMEUNIT.put("m", TimeUnit.MINUTES);
		CONVERT_MAP_FOR_TIMEUNIT.put("s", TimeUnit.SECONDS);
		CONVERT_MAP_FOR_TIMEUNIT.put("ms", TimeUnit.MILLISECONDS);
		CONVERT_MAP_FOR_TIMEUNIT.put("us", TimeUnit.MICROSECONDS);
		CONVERT_MAP_FOR_TIMEUNIT.put("ns", TimeUnit.NANOSECONDS);
	}

	private SleepUtil() {
	}

	public static void sleep(TimeUnit timeUnit, long time) {
		try {
			String lowerCaseTimeUnitStr = timeUnit.toString().toLowerCase();
			LOGGER.info(String.format("[util][time][sleep][begin][%d][%s]",time,lowerCaseTimeUnitStr));
			timeUnit.sleep(time);
			LOGGER.info(String.format("[util][time][sleep][end][%d][%s]",time,lowerCaseTimeUnitStr));
		} catch (InterruptedException e) {
			LOGGER.warn("stop waiting for:" + e.getMessage());
		}
	}

	/**
	 * such as 10s or 10S, it hint 10 seconds
	 *
	 * @param time
	 */
	public static void sleep(String timeStr) {
		ValidationUtil.checkString(timeStr);

		String trimedTimeStr = timeStr.trim();
		String timeUnitStr = trimedTimeStr.replaceAll("\\d", "");
 		String timeNumberStr = trimedTimeStr.replaceAll("\\D", "");

		LOGGER.info(String.format("[util][time][sleep][convert][%s][%s]",timeNumberStr,timeUnitStr));

		sleep(CONVERT_MAP_FOR_TIMEUNIT.get(timeUnitStr.toLowerCase()), Long.valueOf(timeNumberStr));
 	}

 }
