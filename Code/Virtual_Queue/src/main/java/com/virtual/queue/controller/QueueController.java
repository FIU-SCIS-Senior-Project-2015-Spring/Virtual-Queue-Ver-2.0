package com.virtual.queue.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.virtual.queue.beans.RideInfo;
import com.virtual.queue.beans.User;
import com.virtual.queue.exception.NotificationException;
import com.virtual.queue.scheduler.QueueScheduler;
import com.virtual.queue.service.QueueService;
import com.virtual.queue.service.RideService;
import com.virtual.queue.service.VirtualQueueService;

@Controller
@RequestMapping("/queue")
public class QueueController {

	@Autowired
	VirtualQueueService queueService;

	@Autowired
	QueueService qService;

	@Autowired
	RideService rideService;

	@RequestMapping(value = "/removeFromQueue/{barcode}", method = RequestMethod.GET)
	public @ResponseBody Boolean getUserByUserName(
			@PathVariable("barcode") String barcode) {

		// TODO add validation to barcode value to be a number
		if (barcode == null) {

			return false;
		}

		return queueService.pullfromQueue(barcode);

	}

	@RequestMapping(value = "/removeFromQueue/{rideId}/{userId}", method = RequestMethod.GET)
	public @ResponseBody Boolean getUserFromQueue(
			@PathVariable("rideId") long rideId,
			@PathVariable("userId") long userId) {

		return qService.removeUserFromQueue(rideId, userId);

	}

	@RequestMapping(value = "/removeAllFromQueue/{rideId}/{command}", method = RequestMethod.GET)
	public @ResponseBody Boolean getAllUsersFromQueueByRide(
			@PathVariable("rideId") long rideId,
			@PathVariable("command") String command) {

		QueueScheduler qScheduler = new QueueScheduler();

		List<RideInfo> rideList = new ArrayList<RideInfo>();
		RideInfo info;
		try {
			info = rideService.getRidebyId(rideId);

			rideList.add(info);

			return qScheduler.removeUserFromQueue(rideList, command, 15);

		} catch (NotificationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

	@RequestMapping(value = "/removeAll/{command}", method = RequestMethod.GET)
	public @ResponseBody Boolean getAllUsersFromAllQueue(

	@PathVariable("command") String command) {

		QueueScheduler qScheduler = new QueueScheduler();

		List<RideInfo> rideList = new ArrayList<RideInfo>();
		try {
			rideList = rideService.getAll();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return qScheduler.removeUserFromQueue(rideList, command, 15);

	}
}
