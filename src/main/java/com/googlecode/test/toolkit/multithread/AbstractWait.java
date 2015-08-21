package com.googlecode.test.toolkit.multithread;

import java.util.concurrent.TimeUnit;


public abstract class AbstractWait {

	public static final long DEFAULT_INTERVAL = 500l;
	public static final long DEFAULT_TIMEOUT = 30000l;

	public AbstractWait(){}

	public abstract boolean util();

	public void wait(String message){
		wait(message, DEFAULT_TIMEOUT, DEFAULT_INTERVAL);
	}

	public void wait(String message, long timeoutInMillisecond){
		wait(message, timeoutInMillisecond, DEFAULT_INTERVAL);
	}

	public void wait(String messageWhenTimeout, long timeoutInMillisecond, long intervalInMillisecond){
		long start = System.currentTimeMillis();
		long end = start + timeoutInMillisecond;
		while(System.currentTimeMillis() < end){
			if(util()) return;
			else {
				try{
					TimeUnit.MILLISECONDS.sleep(intervalInMillisecond);
				}catch(InterruptedException e){
					throw new RuntimeException(e);
				}
			}
		}
		throw new RuntimeException(messageWhenTimeout);
	}
}
