package com.virtual.queue.dao;

import com.virtual.queue.beans.User;

public interface LoginDao {
      public Long validLogin(String user,String password, String code); 
	  public User signIn(String userName,String password, String code);
	  public boolean signOut(String userName);
	public User adminSignIn(String userName, String password);
}
