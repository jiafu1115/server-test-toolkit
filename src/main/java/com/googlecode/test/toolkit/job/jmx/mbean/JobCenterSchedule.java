package com.googlecode.test.toolkit.job.jmx.mbean;

import com.googlecode.test.toolkit.job.schedule.Schedulable;

public class JobCenterSchedule implements JobCenterScheduleMBean {

	private Schedulable schedule;

	public JobCenterSchedule(Schedulable scheduleImpl) {
		super();
		this.schedule = scheduleImpl;
	}

	@Override
	public void start() {
		schedule.start();
	}

	@Override
	public void stop() {
		schedule.stop();
	}

	@Override
	public String list() {
		return schedule.list();

	}

}
