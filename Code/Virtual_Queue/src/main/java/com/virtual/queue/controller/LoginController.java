package com.virtual.queue.controller;

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
import com.virtual.queue.beans.User;
import com.virtual.queue.request.LoginRequest;
import com.virtual.queue.service.LoginService;

@Controller
@RequestMapping("/login")
@SessionAttributes("user")
public class LoginController {

	@Autowired
	private LoginService loginService;
	public LoginController(){};
	 public LoginController(LoginService service){
		 
		 loginService=service;
		 
	 }

	@RequestMapping(value = "/signin", method = RequestMethod.POST)
	public @ResponseBody ResponseEntity<String> signIn(LoginRequest login,
			HttpServletRequest request, HttpServletResponse response) {

		if (!login.validate())
			return new ResponseEntity<String>(HttpStatus.BAD_REQUEST);

		User user = loginService.signIn(login.getUserName(),
				login.getPassword(), login.getCode());

		if (user == null || user.isNill())
			return new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);

		if (request != null) {

			HttpSession session = request.getSession();
			if (session != null) {
				session.setAttribute("user", user);
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

			User newUser = (User) session.getAttribute("user");

			if (newUser != null && newUser.getUserName().equals(userName)) {

				session.setAttribute("user", "");
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

}
