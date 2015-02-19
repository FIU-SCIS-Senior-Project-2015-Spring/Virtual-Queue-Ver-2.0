package com.virtual.queue.scheduler;

import java.util.List;

import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.Trigger;
import org.quartz.impl.StdSchedulerFactory;

import com.virtual.queue.beans.RideInfo;

public class QueueScheduler {

	public QueueScheduler() {
	}

	public boolean scheduleRideJobs(String command, List<RideInfo> rideList) {

		try {
			Scheduler sch = new StdSchedulerFactory().getScheduler();

			if ("start".equalsIgnoreCase(command) && !sch.isStarted()) {

				sch.start();

			} else {

				if (sch.isStarted()) {
					// wait until all jobs ended before shutting down
					sch.shutdown(true);

				}

			}

			JobDetail job = null;
			Trigger trigger = null;

			for (RideInfo info11 : rideList) {

				job = QueueJobFactory.getJob(QueueJob.class, info11.getrName());
				trigger = QueueTriggerFactory.getTrigger(info11.getInterval(),
						info11.getrName());
				job.getJobDataMap().put(QueueJob.RIDE_ID, info11.getRideId());
				sch.scheduleJob(job, trigger);

			}

		} catch (Exception e) {

			e.printStackTrace();

			// TODO:need to handle custom errors and return them to caller.
			return false;
		}

		return true;
	}

	public boolean removeUserFromQueue(List<RideInfo> ride, String command,
			int delay) {
		// TODO:needs to create message to return to caller.like custom
		// exception in this case.
		if (command == null || ride == null || ride.isEmpty())
			return false;

		try {
			Scheduler sch = new StdSchedulerFactory().getScheduler();
/*
			if ("start".equalsIgnoreCase(command) && !sch.isStarted()) {

				sch.start();

			} else if("stop".equalsIgnoreCase(command)) {

				if (sch.isStarted()) {
					// wait until all jobs ended before shutting down
					sch.shutdown(true);

				}

			}
*/
			JobDetail job = null;
			Trigger trigger = null;
			int interval = 0;
			for (RideInfo info : ride) {

				interval = info.getInterval() + delay;

				job = QueueJobFactory.getJob(RemoveRideJob.class,
						info.getrName()+"_DELETE");

				trigger = QueueTriggerFactory.getTrigger(interval,
						info.getrName()+"_DELETE");
				job.getJobDataMap().put(RemoveRideJob.RIDE_ID, info.getRideId());
				job.getJobDataMap().put(RemoveRideJob.RIDE_INTERVAL, info.getInterval());
				sch.scheduleJob(job, trigger);

			}

		} catch (Exception e) {

			e.printStackTrace();

			// TODO:need to handle custom errors and return them to caller.
			return false;
		}

		return true;

	}

}
