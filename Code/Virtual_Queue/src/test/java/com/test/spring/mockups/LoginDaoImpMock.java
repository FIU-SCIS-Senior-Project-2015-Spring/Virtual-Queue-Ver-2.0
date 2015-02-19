package com.test.spring.mockups;

import com.virtual.queue.beans.User;
import com.virtual.queue.dao.LoginDao;

public class LoginDaoImpMock implements LoginDao {

	@Override
	public Long validLogin(String user, String password, String code) {
		 if(user==null || password ==null || code ==null)return null;
		return new Long(1);
	}

	@Override
	public User signIn(String userName, String password, String code) {
		 if(userName==null || password ==null)return null;
			return User.getDemoUser();
	}

	@Override
	public boolean signOut(String userName) {
		if(userName !=null)return true;
		return false;
	}

	@Override
	public User adminSignIn(String userName, String password) {
	   if(userName==null || password ==null)return null;
		return User.getDemoUser();
	}

}
