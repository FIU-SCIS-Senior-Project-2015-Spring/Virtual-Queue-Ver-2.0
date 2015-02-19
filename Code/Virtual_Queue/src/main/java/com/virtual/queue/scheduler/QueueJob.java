package com.virtual.queue.scheduler;


import org.apache.log4j.Logger;
import org.quartz.DisallowConcurrentExecution;
import org.quartz.Job;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.PersistJobDataAfterExecution;

import com.virtual.queue.service.NotificationServiceImp;
@PersistJobDataAfterExecution
@DisallowConcurrentExecution
public class QueueJob implements Job {
	private Logger log = Logger.getLogger(QueueJob.class);
	
	public  static final String RIDE_ID="RIDE_ID";
  // @Autowired
 //  NotificationServiceImp notificationService;
	// NotificationService service;

	public void execute(JobExecutionContext jExeCtx)
			throws JobExecutionException {

		log.debug("TestJob run successfully...");
		System.out.println("Job ran....");
		JobDataMap map=jExeCtx.getJobDetail().getJobDataMap();
		Long rideId=map.getLong("RIDE_ID");
		//need to get ride id from job context.
		//TODO:check for null or not numeric values..
		ExecuteService(rideId);
	}

	
	private void ExecuteService(long rideId) {

		try {
			 
			new NotificationServiceImp().notifyUser(rideId); 
		
		} catch (Exception e) {
			
			e.printStackTrace();
			
		}

	}

}
