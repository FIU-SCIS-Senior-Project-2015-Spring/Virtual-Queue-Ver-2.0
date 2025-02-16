package com.virtual.queue.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.virtual.queue.beans.RideInfo;
import com.virtual.queue.beans.User;
import com.virtual.queue.beans.UserQueueInfo;
import com.virtual.queue.dao.QueueDao;
import com.virtual.queue.dao.RideDao;
import com.virtual.queue.request.LoginRequest;
import com.virtual.queue.service.AdminService;
import com.virtual.queue.service.LoginService;
import com.virtual.queue.service.NotificationService;
import com.virtual.queue.service.RideService;

@Controller
@RequestMapping("/admin")
@SessionAttributes("user")
public class AdminController {

	@Autowired
	private LoginService loginService;
	
	@Autowired
	private AdminService adminService;
	
	@Autowired
	private RideService rideService;
	
	@Autowired
	RideDao rideDao;
	
	@Autowired
	NotificationService notifService;
	
	@Autowired
	QueueDao queueDao;


	@RequestMapping(value = "/signin", method = RequestMethod.POST)
	public @ResponseBody ResponseEntity<String> signIn(LoginRequest login,
			HttpServletRequest request, HttpServletResponse response) {

			String userName = login.getUserName();
			String password = login.getPassword();
			
			if (userName == null || password == null){
				
				return new ResponseEntity<String>(HttpStatus.BAD_REQUEST);
			}
		User user = loginService.adminSignIn(userName, password);
		

		if (user== null || user.isNill())
			return new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);

		if (!user.getUserRole().getRoleType().equals("Admin")) {

			return new ResponseEntity<String>(HttpStatus.BAD_REQUEST);

		}

		if (request != null) {

			HttpSession session = request.getSession();
			if (session != null) {
				session.setAttribute("Admin", user);
			} else {

				return new ResponseEntity<String>(
						HttpStatus.INTERNAL_SERVER_ERROR);
			}

		}

		JSONObject obj = new JSONObject();
		obj.append("user_name", user.getUserName());
		obj.append("user_id", user.getUserid().toString());
		obj.append("user_email", user.getEmail());
		obj.append("role", user.getUserRole().getRoleType());
		// finish adding all roles returned from GET-USER SELECT
		return new ResponseEntity<String>(obj.toString(), HttpStatus.OK);

	}
	
	@RequestMapping(value = "/addrecords", method = RequestMethod.POST)
	public @ResponseBody ResponseEntity<String> addrecords(int recnumber, int simridename,
			HttpServletRequest request, HttpServletResponse response) {
		System.out.println("Add Records");
		System.out.println("RecNumaber: "+ recnumber);
		System.out.println("Ride num: "+ simridename);
		if(recnumber>=100){
			return new ResponseEntity<String>("Adding to much record",HttpStatus.BAD_REQUEST);
		}
		for(int x=3; x<recnumber+3;x++){
			try {
				rideService.addUserRideById((long)simridename, (long)x, false);

				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}	
		}
		
		
		return new ResponseEntity<String>("ok", HttpStatus.OK);

	}
	
	@RequestMapping(value = "/adminDequeue", method = RequestMethod.POST)
	public @ResponseBody ResponseEntity<String> adminDequeue(
			@RequestParam(value = "ride_id") Long rideId, 
			@RequestParam(value = "maxCpty") Long maxCpty,
			@RequestParam(value = "totalRecord") Long totalRecord,
			@RequestParam(value = "interval") Long interval) {
		System.out.println("ride_id" + rideId);
		System.out.println(maxCpty);
//		System.out.println("RecNumaber: "+ recnumber);
//		System.out.println("Ride num: "+ simridename);
		long totalCap = maxCpty * interval;
		if(totalRecord <= totalCap){
			//return new ResponseEntity<String>("Not enough patrons to fill ride capacity, check again soon!",HttpStatus.BAD_REQUEST);
			for(Long x=totalCap; x> 0;x--){
				try {
					  rideService.removeFromFront(rideId);

				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}	
			}
		}
		
		for(Long x=totalCap; x> 0;x--){
			try {
				  rideService.removeFromFront(rideId);

			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}	
		}
		
		try {
			rideService.setUpToSendNotification(rideId);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		
		return new ResponseEntity<String>("ok", HttpStatus.OK);

	}
	@RequestMapping(value = "/adminEdit", method = RequestMethod.POST)
	public @ResponseBody ResponseEntity<String> adminEdit(
//			@RequestParam(value = "ride_id") Long rideId,
//			@RequestParam(value = "waitTime") Long waitTime,
//			@RequestParam(value = "totalRecord") Long totalRecord,
//			@RequestParam(value = "timePerEvent") Long timePerEvent,
//			@RequestParam(value = "entryTime") Long entryTime,
//			@RequestParam(value = "exitTime") Long exitTime,
//			@RequestParam(value = "maxCpty") Long maxCpty,
//			@RequestParam(value = "concRide") Long concRide) {
			Long activityNum, String rideName, Long concRec, Long entryTime, Long exitTime, Long maxCpty,  Long interval,
			HttpServletRequest request, HttpServletResponse response) {
		System.out.println("ride_id" + activityNum);
		System.out.println(rideName);
		System.out.println(maxCpty);
		try {
			  rideService.editRide(activityNum, rideName, concRec, entryTime, exitTime, maxCpty, interval);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		
		return new ResponseEntity<String>("ok", HttpStatus.OK);

	}

	@RequestMapping(value = "/signout", method = RequestMethod.POST)
	public @ResponseBody ResponseEntity<String> signOut(
			@RequestParam(value = "userName") String userName,
			HttpServletRequest request) {

		if (userName == null || "".equals(userName)) {

			return new ResponseEntity<String>(HttpStatus.BAD_REQUEST);

		}
		if (request != null) {

			HttpSession session = request.getSession();
			if (session == null) {
				return new ResponseEntity<String>(
						HttpStatus.INTERNAL_SERVER_ERROR);
			}

			User newUser = (User) session.getAttribute("Admin");

			if (newUser != null && newUser.getUserName().equals(userName)) {

				session.setAttribute("Admin", "");
				session.invalidate();
			} else {
				// TODO:check return status code for find grain values.
				return new ResponseEntity<String>(
						HttpStatus.INTERNAL_SERVER_ERROR);

			}

		}

		return new ResponseEntity<String>(HttpStatus.OK);
	}

	@RequestMapping("/layout")
	public String getIndexPage() {
		return "login/layout";

	}

	
	@RequestMapping(value = "/allUsers", method = RequestMethod.GET)
	public @ResponseBody List<User> getAllUsers() {

		List<User> list = new ArrayList<User>();
		try {
			list =  adminService.getAll();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}
	
	@RequestMapping(value = "/allRides", method = RequestMethod.GET)
	public @ResponseBody List<RideInfo> getAllRides() {
		System.out.println("admin all rides");
		List<RideInfo> list = new ArrayList<RideInfo>();
		try {
			list =  rideService.getAll();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}
	@RequestMapping(value = "/setMultipleRecords", method = RequestMethod.POST)
	public @ResponseBody List<User> setMultipleRecords() {

		List<User> list = new ArrayList<User>();
		try {
			list =  adminService.getAll();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}
	
	
}
