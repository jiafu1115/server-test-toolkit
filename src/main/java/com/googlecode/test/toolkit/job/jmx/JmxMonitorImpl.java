package com.googlecode.test.toolkit.job.jmx;

import java.lang.management.ManagementFactory;

import javax.management.JMException;
import javax.management.MBeanServer;
import javax.management.ObjectName;

public class JmxMonitorImpl implements JmxMonitor {

	private final static MBeanServer MBEAN_SERVER=ManagementFactory.getPlatformMBeanServer();
	private final static JmxMonitor INSTANCE=new JmxMonitorImpl();

 	public static JmxMonitor getInstance() {
		return INSTANCE;
	}

	private JmxMonitorImpl(){

	}

	public void registerMBean(Object object, ObjectName name) throws JMException{
		try {
			MBEAN_SERVER.registerMBean(object, name);
		} catch (JMException e) {
			throw e;
 		}
	}
  }
