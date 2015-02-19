package com.virtual.queue.service;

import com.virtual.queue.beans.User;

public interface LoginService {
	public User signIn(String userName, String password, String code);

	public boolean signOut(String userName);

	User adminSignIn(String userName, String password);

}
