package com.googlecode.test.toolkit.casewrapper.testng;

import org.apache.log4j.Logger;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

/**
 <pre>
            <plugin>
                <groupId>org.apache.maven.plugins</groupId>
                <artifactId>maven-surefire-plugin</artifactId>
                <version>2.15</version>
                <configuration>
                    <suiteXmlFiles>
                         <suiteXmlFile>${testNG.file}</suiteXmlFile>
                    </suiteXmlFiles>
                     <properties>
                        <property>
                            <name>usedefaultlisteners</name>
                            <value>true</value>
                            <!--  disabling default listeners is optional -->
                        </property>
                        <property>
                            <name>listener</name>
                            <value>com.googlecode.test.toolkit.casewrapper.testng.TestLogListener</value>
                        </property>
                    </properties>

                </configuration>
            </plugin>
 </pre>

 * @author jiafu
 *
 */
public class TestLogListener implements ITestListener {

	private static final Logger LOGGER = Logger.getLogger(TestLogListener.class);

     @Override
    public void onStart(ITestContext arg0) {
         LOGGER.info(String.format(LogConstants.LOG_FORMAT_FOR_TEST_START,arg0.getStartDate()));
    }

    @Override
    public void onFinish(ITestContext arg0) {
         LOGGER.info(String.format(LogConstants.LOG_FORMAT_FOR_TEST_FINISH,arg0.getEndDate(), (arg0.getEndDate().getTime()-arg0.getStartDate().getTime())/1000));
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult arg0) {
        String currentMethodName = Thread.currentThread().getStackTrace()[1].getMethodName();
        logWithoutConsumedTime(arg0, currentMethodName);
    }

    private void logWithoutConsumedTime(ITestResult iTestResult, String currentMethodName) {
        LOGGER.info(String.format(LogConstants.LOG_FORMAT_FOR_WITHOUT_CONSUMED_TIME, getTestMethodName(iTestResult), currentMethodName));
    }

    private void logWithConsumedTime(ITestResult arg0, String currentMethodName) {
        LOGGER.info(String.format(LogConstants.LOG_FORMAT_FOR_CONSUMED_TIME, getTestMethodName(arg0), currentMethodName,arg0.getEndMillis()-arg0.getStartMillis()));
    }

    @Override
    public void onTestFailure(ITestResult arg0) {
        String currentMethodName = Thread.currentThread().getStackTrace()[1].getMethodName();
        logWithConsumedTime(arg0, currentMethodName);
    }

    @Override
    public void onTestSkipped(ITestResult arg0) {
        String currentMethodName = Thread.currentThread().getStackTrace()[1].getMethodName();
        logWithoutConsumedTime(arg0, currentMethodName);
    }

    private String getTestMethodName(ITestResult iTestResult) {
        return iTestResult.getTestClass().getRealClass().getName()+"."+iTestResult.getMethod().getMethodName();
    }

    @Override
    public void onTestStart(ITestResult iTestResult) {
        String currentMethodName = Thread.currentThread().getStackTrace()[1].getMethodName();
        logWithoutConsumedTime(iTestResult, currentMethodName);
    }

    @Override
    public void onTestSuccess(ITestResult arg0) {
         String currentMethodName = Thread.currentThread().getStackTrace()[1].getMethodName();
         logWithConsumedTime(arg0, currentMethodName);
    }

}
