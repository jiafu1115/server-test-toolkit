package com.googlecode.test.toolkit.casewrapper.testng;

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
                            <value>com.googlecode.test.toolkit.casewrapper.testng.TestFailLogAppendListener</value>
                        </property>
                    </properties>

                </configuration>
            </plugin>
 </pre>

 * @author jiafu
 *
 */
public class TestFailLogAppendListener implements ITestListener {


     @Override
    public void onStart(ITestContext arg0) {
     }

    @Override
    public void onFinish(ITestContext arg0) {
     }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult arg0) {
    }

    @Override
    public void onTestFailure(ITestResult arg0) {
        String property = System.getProperty("appendInfo");
        if(property!=null&&!property.isEmpty()){
             arg0.setThrowable(new Exception(arg0.getThrowable().getMessage()+":"+property, arg0.getThrowable()));
         }
    }

    @Override
    public void onTestSkipped(ITestResult arg0) {
    }

    @Override
    public void onTestStart(ITestResult iTestResult) {
     }

    @Override
    public void onTestSuccess(ITestResult arg0) {
     }

}
