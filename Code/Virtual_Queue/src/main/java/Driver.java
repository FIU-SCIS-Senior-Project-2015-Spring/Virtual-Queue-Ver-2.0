

import java.util.List; 

import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.impl.StdSchedulerFactory;

import com.virtual.queue.beans.RideInfo;
import com.virtual.queue.exception.NotificationException;
import com.virtual.queue.scheduler.QueueJob;
import com.virtual.queue.scheduler.QueueJobFactory;
import com.virtual.queue.scheduler.QueueTriggerFactory;
import com.virtual.queue.service.RideService;
import com.virtual.queue.service.RideServiceImp;

public class Driver {

	public static void main(String[] args) {

		System.out.println("I am here!!!!!!!!!!!!!!!");
		try {

			// schedule the job
			//SchedulerFactory schFactory = new StdSchedulerFactory();
			try {
			Scheduler sch = new StdSchedulerFactory().getScheduler();

			sch.start();

			//RideService service = new RideServiceImp();
			List<RideInfo> list;
			
				list = new RideServiceImp().pullRideInfo();
			

			 JobDetail job = null;
			// Trigger trigger = null;

			for (RideInfo info : list) {

				job = QueueJobFactory.getJob(QueueJob.class,
				 info.getrName());
				//trigger = QueueTriggerFactory.getTrigger(info.getInterval());
				job.getJobDataMap().put(QueueJob.RIDE_ID,info.getRideId());
				
				
				sch.scheduleJob( job,
						QueueTriggerFactory.getTrigger(info.getInterval(),info.getrName()));
				job.getJobDataMap().put(QueueJob.RIDE_ID,info.getRideId());
			}

		} catch (SchedulerException e) {

			e.printStackTrace();

		}
		} catch (NotificationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
