package com.googlecode.test.toolkit.util;

public final class MemoryUtil {

	public static long getAvailableMemory(){
		Runtime runtime = Runtime.getRuntime();
		return runtime.maxMemory()-runtime.totalMemory()+runtime.freeMemory();
	}

	private MemoryUtil() {
 	}
}
