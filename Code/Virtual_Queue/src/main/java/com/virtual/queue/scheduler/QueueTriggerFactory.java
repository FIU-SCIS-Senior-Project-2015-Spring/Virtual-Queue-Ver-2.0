package com.virtual.queue.scheduler;

import java.util.Date;

import org.quartz.SimpleScheduleBuilder;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;

public class QueueTriggerFactory {

	private QueueTriggerFactory() {
	};

	public static Trigger getTrigger(int interval,String name) {

		return TriggerBuilder.newTrigger().withIdentity(name)

		.withSchedule(SimpleScheduleBuilder.simpleSchedule()

		.withIntervalInSeconds(interval)

		.repeatForever())

		.build();

	}

	public static Trigger getTrigger(String rideName,int interval, Date startTime, Date endTime) {

		// TODO need to pass start time ,end time to jobs when scheduling.
		return TriggerBuilder.newTrigger().withIdentity(rideName)

		.withSchedule(SimpleScheduleBuilder.simpleSchedule()

		.withIntervalInSeconds(interval)

		.repeatForever())

		.build();

	}

}
