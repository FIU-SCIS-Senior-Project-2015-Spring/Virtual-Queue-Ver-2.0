package com.virtual.queue.scheduler;

import org.quartz.JobBuilder;
import org.quartz.JobDetail;

public class QueueJobFactory {
    private QueueJobFactory(){};
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static JobDetail getJob(Class clazz,String jobName) {
	
		return JobBuilder.newJob(clazz)

		.withIdentity(jobName)

		.build();
		
		 
	}
	@SuppressWarnings("unchecked")
	public static JobDetail getJob(@SuppressWarnings("rawtypes") Class clazz,String jobName,String group) {
		
		return JobBuilder.newJob(clazz)

		.withIdentity(jobName,group)

		.build();
		
		 
	}
}
