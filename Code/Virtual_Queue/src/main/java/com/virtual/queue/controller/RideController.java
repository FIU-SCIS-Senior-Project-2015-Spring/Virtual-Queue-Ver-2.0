package com.virtual.queue.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.virtual.queue.beans.Ride;
import com.virtual.queue.beans.RideInfo;
import com.virtual.queue.beans.User;
import com.virtual.queue.exception.NotificationException;
import com.virtual.queue.service.RideService;

@Controller
@RequestMapping("/ride")
public class RideController {
	@Autowired
	private RideService rideService;
public RideController(){
				
	}
public RideController(RideService service){
		
		rideService=service;		
		
	}

	@RequestMapping(value = "/rides", method = RequestMethod.GET)
	public @ResponseBody List<RideInfo> getAllRides() {

		List<RideInfo> list = new ArrayList<RideInfo>();
		try {
			list = rideService.getAll();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	@RequestMapping(value = "/user/rides", method = RequestMethod.POST)
	public @ResponseBody List<RideInfo> getRideByUser(
			@RequestParam(value = "userid") Long userId) {

		List<RideInfo> list = new ArrayList<RideInfo>();
		try {

			list = rideService.getRidesByUser(userId);

		} catch (NotificationException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	@RequestMapping(value = "/user/rides/{id}", method = RequestMethod.GET)
	public @ResponseBody List<RideInfo> getRideByUserID(
			@PathVariable(value = "id") Long userId) {

		List<RideInfo> list = new ArrayList<RideInfo>();
		try {
			list = rideService.getRidesByUser(userId);
		} catch (NotificationException e) {			
			e.printStackTrace();
		} catch (Exception e) {		
			e.printStackTrace();
		}
		return list;
	}

	@RequestMapping(value = "/addRide", method = RequestMethod.POST)
	public @ResponseBody void addRide(@RequestBody Ride ride,
			@ModelAttribute User user) {
		rideService.addRide(ride);

	}

	@RequestMapping(value = "/updateRide", method = RequestMethod.PUT)
	public @ResponseBody void updateRide(@RequestBody Ride ride) {
		rideService.updateRide(ride);
	}

	@RequestMapping(value = "/removeRide/{rideid}/{userid}", method = RequestMethod.GET)
	public @ResponseBody void removeRideByUser(
			@PathVariable("rideid") Long rideId,
			@PathVariable("userid") Long userid) {
		rideService.deleteRideById(rideId, userid);
	}

	@RequestMapping(value = "/removeRideByUser", method = RequestMethod.POST)
	@ResponseBody
	public boolean removeRideByUserID(
			@RequestParam(value = "rideId") Long rideId,
			@RequestParam(value = "userid") Long userid) {

		try {

			rideService.deleteRideById(rideId, userid);

		} catch (Exception ex) {

			return false;

		}
		return true;
	}

	@RequestMapping(value = "/addRide/{rideid}/{userid}", method = RequestMethod.GET)
	public @ResponseBody boolean addrideByUserGet(
			@PathVariable("rideid") Long rideId,
			@PathVariable("userid") Long userid) {

		try {

			return rideService.addUserRideById(rideId, userid);

		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@RequestMapping(value = "/addUser", method = RequestMethod.POST)
	public @ResponseBody boolean addrideByUser(
			@RequestParam(value = "rideid") Long rideId,
			@RequestParam(value = "userid") Long userId) {

		try {
			return rideService.addUserRideById(rideId, userId);

		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@RequestMapping(value = "/removeRide/{id}", method = RequestMethod.DELETE)
	public @ResponseBody Boolean removeRideById(@PathVariable("id") String id) {
		return rideService.removeRidebyId(id);
	}

	@RequestMapping("/layout")
	public String getUserPartialPage(ModelMap modelMap) {
		return "users/layout";
	}
}
