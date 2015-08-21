package com.googlecode.test.toolkit.job.config;

import java.util.List;
import java.util.Map;

import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.Trigger;

/**
 * tester can implement this interface to provide several jobs.
 * @author fu.jian
 *
 */
public interface Job {

	Map<JobDetail, List<Trigger>> getJobDetails(Scheduler scheduler);

}
