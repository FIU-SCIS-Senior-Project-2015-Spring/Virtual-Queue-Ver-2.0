package com.virtual.queue.scheduler;

import java.util.List;

import org.apache.log4j.Logger;
import org.quartz.Job;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import com.virtual.queue.service.NotificationServiceImp;
import com.virtual.queue.service.QueueService;
import com.virtual.queue.service.QueueServiceImp;

public class RemoveRideJob implements Job {
	private Logger log = Logger.getLogger(QueueJob.class);

	// @Autowired
	// NotificationServiceImp notificationService;
	// NotificationService service;
	public static final String RIDE_ID = "RIDE_ID";
	public static final String RIDE_INTERVAL="INTERVAL";

	public void execute(JobExecutionContext jExeCtx)
			throws JobExecutionException {

		log.debug("TestJob run successfully...");
		System.out.println("Job ran....");
		JobDataMap map = jExeCtx.getJobDetail().getJobDataMap();
		
		Long rideId = map.getLong("RIDE_ID");
		int interval=map.getInt("INTERVAL");
		// need to get ride id from job context.
		// TODO:check for null or not numeric values..
		ExecuteService(rideId,interval);
	}

	private void ExecuteService(long rideId,int interval) {

		try {
			QueueService queueServ = new QueueServiceImp();

			// get all users for that ride.
			List<Long> userList = queueServ.getUsersFromQueue(rideId, 20);

			for (Long id : userList) {

				queueServ.removeUserFromQueue(rideId, id);

			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
