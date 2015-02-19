package com.test.spring.mockups;

import java.util.ArrayList;
import java.util.List;

import com.virtual.queue.beans.User;
import com.virtual.queue.request.UserPasswordResetRequest;
import com.virtual.queue.request.UserRequest;
import com.virtual.queue.service.UserService;

public class UserServiceImpMock implements UserService {

	@Override
	public User getUser(String username, String passwd) {
		if (username == null || passwd == null)
			return null;
		User user = new User();
		user.setFirstName("FirstName");
		return user;
	}

	@Override
	public String getCurrentlyAuthenticatedUserName() {
		// TODO Auto-generated method stub
		return "Current";
	}

	@Override
	public User getUserByToken(String token) {
		 
		User user = new User();
		user.setFirstName("FirstName");
		user.setToken(token);
		return user;
	}

	@Override
	public String storeToken(long userId) {
		 
		return "token";
	}

	@Override
	public List<User> getAll() {
		 
		List<User> list = new ArrayList<User>();
		
		User user = new User();
		user.setFirstName("FirstName");
		list.add(user);
		return list;
		
		
		
	}

	@Override
	public void addUser(User user) {
		// TODO Auto-generated method stub

	}

	@Override
	public void updateUser(User user) {
		// TODO Auto-generated method stub

	}

	@Override
	public void deleteUserById(Long id) {
		// TODO Auto-generated method stub

	}

	@Override
	public User getUserByUserName(String userName) {
		if(userName ==null)return null;
		
		User user = new User();
		user.setFirstName("FirstName");
		 
		return user;
	}

	@Override
	public void resetPassword(UserPasswordResetRequest passwordReset)
			throws Exception {
		if(passwordReset==null)throw new Exception("Invalid input");

	}

	@Override
	public boolean removeUserFromQueue(long rideId, long userId) {
        if(rideId ==0 || userId==0)return false;
		return true;
	}

	@Override
	public User getUserById(Long userId) {
		if (userId == 0)
			return null;
		User user = new User();
		user.setFirstName("FirstName");
		return user;
	}

	@Override
	public void editUserInfo(UserRequest editInfo) throws Exception {
		if(editInfo==null)throw new Exception("Invalid input");

	}

	@Override
	public boolean enabledDisabledUser(long userId, String flag) {
		if (userId == 0)
			return false;
		return true;
	}

}
