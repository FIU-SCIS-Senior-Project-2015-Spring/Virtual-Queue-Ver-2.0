package com.virtual.queue.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

//import com.virtual.queue.beans.AbstractUser;
//import com.virtual.queue.beans.NullUser;
import com.virtual.queue.beans.User;
import com.virtual.queue.request.UserPasswordResetRequest;
import com.virtual.queue.request.UserRequest;
//import com.virtual.queue.response.UserResponse;
import com.virtual.queue.service.UserService;

@Controller
@RequestMapping("/user")
public class UserController {
	@Autowired
	private UserService userService;

	public UserController() {
	}

	public UserController(UserService service) {

		userService = service;

	}

	@RequestMapping(value = "/userList.json", method = RequestMethod.GET)
	public List<User> getAllUsers() {
		return userService.getAll();
	}

	@RequestMapping(value = "/addUser", method = RequestMethod.POST)
	public @ResponseBody void addUser(@RequestBody User user) {
		userService.addUser(user);
	}

	@RequestMapping(value = "/updateUser", method = RequestMethod.POST)
	public @ResponseBody void updateUser(@RequestBody User user) {
		userService.updateUser(user);
	}

	@RequestMapping(value = "/removeUser/{id}", method = RequestMethod.DELETE)
	public @ResponseBody void removeUser(@PathVariable("id") Long id) {
		userService.deleteUserById(id);
	}

	@RequestMapping(value = "/getUserByUserName/{uName}", method = RequestMethod.GET)
	public @ResponseBody User getUserByUserName(
			@PathVariable("uName") String userName) {
		return userService.getUserByUserName(userName);
	}

	@RequestMapping(value = "/add", method = RequestMethod.POST)
	@ResponseBody
	public Boolean savePerson(User user) {

		try {

			userService.addUser(user);

		} catch (Exception e) {

			e.printStackTrace();
			return false;
		}

		return true;
	}

	@RequestMapping(value = "/resetPassword", method = RequestMethod.POST)
	@ResponseBody
	public Boolean resetPassword(UserPasswordResetRequest passwordReset) {

		try {

			userService.resetPassword(passwordReset);

		} catch (Exception e) {

			e.printStackTrace();

			return false;

		}
		return true;

	}

	@RequestMapping(value = "/editUser", method = RequestMethod.POST)
	@ResponseBody
	public Boolean editUser(UserRequest editInfo) {

		try {

			userService.editUserInfo(editInfo);

		} catch (Exception e) {

			e.printStackTrace();

			return false;

		}
		return true;

	}

	@RequestMapping(value = "/getUserById/{userId}", method = RequestMethod.GET)
	public @ResponseBody User getUserById(@PathVariable("userId") Long userId) {
		return userService.getUserById(userId);
	}

	@RequestMapping(value = "/enable/{userId}/{flag}", method = RequestMethod.GET)
	public @ResponseBody boolean enabledDisabledUser(
			@PathVariable("flag") String flag,
			@PathVariable("userId") long userId) {
		return userService.enabledDisabledUser(userId, flag);
	}

}
