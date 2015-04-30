package com.virtual.queue.listener;

import java.util.List;
import org.apache.log4j.Logger;
import com.virtual.queue.beans.RideInfo;
import com.virtual.queue.scheduler.QueueScheduler;
import com.virtual.queue.service.RideServiceImp;

public class QueueContextLoaderListener extends
		org.springframework.web.context.ContextLoaderListener {
	final static Logger logger = Logger
			.getLogger(QueueContextLoaderListener.class);

	// @Autowired
	// RideService rideService;

	public QueueContextLoaderListener() {

	}

	@SuppressWarnings("unused")
	private void RunScheduler() {

		logger.info("Starting application...");
		System.out.println("Application started.........s");
		// schedule the job
		try {
			// TODO:prefer service injection.
			List<RideInfo> list = new RideServiceImp().pullRideInfo();
			// TODO:use enum actions..
			new QueueScheduler().scheduleRideJobs("start",
					new RideServiceImp().pullRideInfo());
		} catch (Exception ex) {

			ex.printStackTrace();
			System.out.println(ex.getMessage());

		}

	}
}