package com.googlecode.test.toolkit.job.jmx;

import javax.management.JMException;
import javax.management.ObjectName;

public interface JmxMonitor {

	void registerMBean(Object object, ObjectName name) throws JMException;
}
