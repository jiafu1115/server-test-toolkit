package com.googlecode.test.toolkit.util;

public final class TrackIdUtil {

	private final static ThreadLocal<Integer> THREAD_LOCAL_VARS = new ThreadLocal<Integer>() {
		@Override
		protected Integer initialValue() {
			return 0;
		};

	};

	public static int current() {
		return THREAD_LOCAL_VARS.get();
	}

 	public static int next() {
		THREAD_LOCAL_VARS.set(THREAD_LOCAL_VARS.get() + 1);
		return THREAD_LOCAL_VARS.get();
	}

	private TrackIdUtil() {
	}

}