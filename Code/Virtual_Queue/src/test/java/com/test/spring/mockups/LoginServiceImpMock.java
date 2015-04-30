package com.test.spring.mockups;

import com.virtual.queue.beans.User;
import com.virtual.queue.service.LoginService;

public class LoginServiceImpMock implements LoginService {

	@Override
	public User signIn(String userName, String password, String code) {

		if (userName == null || password == null)
			return null;

		User user = new User();
		user.setUserid(1l);
		user.setFirstName("FirstName");
		return user;
	}

	@Override
	public boolean signOut(String userName) {

		if (userName == null)
			return false;
		return true;
	}

	@Override
	public User adminSignIn(String userName, String password) {
		if (userName == null)
			return null;
		User user = new User();
		user.setUserid(1l);
		user.setFirstName("FirstName");

		return user;
	}

}
