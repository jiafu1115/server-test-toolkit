package com.googlecode.test.toolkit.job;

import org.quartz.CronScheduleBuilder;
import org.quartz.CronTrigger;
import org.quartz.Job;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.JobKey;
import org.quartz.JobListener;
import org.quartz.Matcher;
import org.quartz.ScheduleBuilder;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.impl.matchers.KeyMatcher;

import com.googlecode.test.toolkit.constants.MarkConstants;
import com.googlecode.test.toolkit.job.exception.JobConfigException;

public final class JobUtil {

	private static final String JOB = "job";
	private static final String _ = MarkConstants.UNDERLINE;

	static void appendListenerToJob(Scheduler scheduler, JobDetail jobDetail,
			JobListener jobListener) {
		Matcher<JobKey> matcher = KeyMatcher.keyEquals(jobDetail.getKey());
		try {
			scheduler.getListenerManager().addJobListener(jobListener, matcher);
		} catch (SchedulerException e) {
 			throw new JobConfigException("add listener fail:", e);
		}
	}

	static String formatJobName(String jobName) {
		StringBuilder stringBuilder = new StringBuilder(JOB);
		stringBuilder.append(_);
		stringBuilder.append(jobName);

		return stringBuilder.toString();
	}

	static Trigger getDefaultTrigger(String cronTab) {
		ScheduleBuilder<CronTrigger> schedBuilder = CronScheduleBuilder.cronSchedule(cronTab);
		Trigger trigger = TriggerBuilder.newTrigger().withSchedule(schedBuilder).build();

		return trigger;
	}

	static JobDetail getDefaultJobDetail(Class<? extends Job> jobClass, String jobName) {
		JobDetail job = JobBuilder.newJob(jobClass).withIdentity(jobName).build();
		return job;
	}

	private JobUtil() {
	}

}
