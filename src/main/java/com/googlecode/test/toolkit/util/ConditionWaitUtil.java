package com.googlecode.test.toolkit.util;

import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;

/**
 *  <code>
     Condition condition=new ConditionWaitUtil.Condition() {

            @Override
            protected boolean checkCondition() {
                  return false;
            }
        };

    ConditionWaitUtil.wait(condition, 2*1000, 10*1000);
    </code>
 * @author pholen.wang jiafu
 *
 */
public final class ConditionWaitUtil {

    private final static Logger LOGGER = Logger.getLogger(ConditionWaitUtil.class);

    private ConditionWaitUtil(){
        //no instance;
    }

    public static abstract class Condition{
        protected abstract boolean checkCondition();
     }

    public static class WaitTimeoutException extends RuntimeException{

        private static final long serialVersionUID = 1L;

        public WaitTimeoutException() {
        }

        public WaitTimeoutException(String arg0) {
            super(arg0);
        }

        public WaitTimeoutException(Throwable arg0) {
            super(arg0);
        }

        public WaitTimeoutException(String arg0, Throwable arg1) {
            super(arg0, arg1);
        }

    }


    /**
     * consume time is not real consume time, it depends on intervalInMilliseconds.
     * @param condition
     * @param intervalInMilliseconds
     * @param timeoutInMilliseconds
     */
    public static void wait(Condition condition,long intervalInMilliseconds, long timeoutInMilliseconds) {
        LOGGER.info("[util][wait][start]"+condition);
        long startTime = System.currentTimeMillis();
        long endTime = startTime + timeoutInMilliseconds;

        while (System.currentTimeMillis() < endTime) {
            if (condition.checkCondition()) {
                LOGGER.info("[util][wait][end][success]condition:["+condition+"] consumed "+(System.currentTimeMillis()-startTime)+"(ms)");
                return;
            }
            try {
                TimeUnit.MILLISECONDS.sleep(intervalInMilliseconds);
            } catch (InterruptedException e) {
                LOGGER.info("[util][wait][end][fail]"+condition);
                throw new RuntimeException(e);
            }
        }

        String logfailMsg = "[util][wait][end][fail]condition:["+condition+"] after "+timeoutInMilliseconds+"(ms)";
		LOGGER.info(logfailMsg);
        throw new WaitTimeoutException(logfailMsg);
    }

}
