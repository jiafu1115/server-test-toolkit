package com.googlecode.test.toolkit.job.config;

import com.googlecode.test.toolkit.job.exception.JobConfigException;

/**
 * @author fu.jian
 * date Aug 17, 2012
 * @param <T>
 * @see "job.xml"
 */
public class JobEntry<T> {

	/**
	 * job name
	 */
	private String name;
	/**
	 * job className
	 */
	private String className;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public T getClassInstance() {
		try {
			@SuppressWarnings("unchecked")
			Class<T> clazz = (Class<T>) Class.forName(className);
			return clazz.newInstance();
		} catch (Exception e) {
			throw new JobConfigException(e.getMessage(), e);
		}
	}

	@Override
	public String toString() {
		return "JobEntry [name=" + name + ", className=" + className + "]";
	}

}
