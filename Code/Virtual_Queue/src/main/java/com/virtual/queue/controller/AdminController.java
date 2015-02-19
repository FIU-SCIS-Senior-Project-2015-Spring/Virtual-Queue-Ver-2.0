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
import com.virtual.queue.request.LoginRequest;
import com.virtual.queue.service.AdminService;
import com.virtual.queue.service.LoginService;

@Controller
@RequestMapping("/admin")
@SessionAttributes("user")
public class AdminController {

	@Autowired
	private LoginService loginService;
	
	@Autowired
	private AdminService adminService;

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

		if (!user.getUserRole().getRoleType().equals("ADMIN")) {

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
	
}
