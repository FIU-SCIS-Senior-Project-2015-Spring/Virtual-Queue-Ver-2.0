package com.virtual.queue.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.virtual.queue.beans.RideInfo;
import com.virtual.queue.beans.User;
import com.virtual.queue.exception.NotificationException;
import com.virtual.queue.listener.QueueContextLoaderListener;
import com.virtual.queue.scheduler.QueueScheduler;
import com.virtual.queue.service.RideService;

@Controller
@RequestMapping("/notification")
public class NotificationController {
	@Autowired
	RideService rideService;
	
	
	final static Logger logger = Logger
			.getLogger(QueueContextLoaderListener.class);

	@RequestMapping(value = "/notify/{command}", method = RequestMethod.GET)
	@ResponseBody
	public Boolean notificationEngine(@PathVariable("command") String command) {

		logger.info("Starting application...");
		System.out.println("Application started.........");

		QueueScheduler qScheduler = new QueueScheduler();
		List<RideInfo> rides = new ArrayList<RideInfo>();
		try {
			rides = rideService.pullRideInfo();
		} catch (NotificationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		 qScheduler.scheduleRideJobs(command, rides);
		 qScheduler.removeUserFromQueue(rides, command, 15);
		 return true;
	}

	@RequestMapping(value = "/notify", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<String> StartNotificationEngine(
			@RequestParam("command") String command,
			@RequestParam(value = "userid") long userId,
			HttpServletRequest request) {

		if (userId == 0 || command == null || "".equalsIgnoreCase(command)) {
			return new ResponseEntity<String>(HttpStatus.BAD_REQUEST);

		}
		if (request != null) {

			HttpSession session = request.getSession();
			if (session == null) {
				return new ResponseEntity<String>(
						HttpStatus.INTERNAL_SERVER_ERROR);
			}

			User newUser = (User) session.getAttribute("user");

			if (newUser != null && newUser.getUserid() == userId) {

				logger.info("Starting application...");
				System.out.println("Application started.........");
				// need to use injection
				try {
					new QueueScheduler().scheduleRideJobs(command,
							rideService.pullRideInfo());
				} catch (NotificationException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					return new ResponseEntity<String>(
							HttpStatus.INTERNAL_SERVER_ERROR);
				}

			} else {
				// TODO:check return status code for find grain values.
				return new ResponseEntity<String>(
						HttpStatus.INTERNAL_SERVER_ERROR);

			}

		}

		return new ResponseEntity<String>(HttpStatus.OK);

	}

}
