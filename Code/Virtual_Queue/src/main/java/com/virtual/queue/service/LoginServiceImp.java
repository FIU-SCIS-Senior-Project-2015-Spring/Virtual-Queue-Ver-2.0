package com.virtual.queue.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.virtual.queue.beans.User;
import com.virtual.queue.dao.LoginDao;

@Service
public class LoginServiceImp implements LoginService {
@Autowired
LoginDao logindao;
public LoginServiceImp(){};
public LoginServiceImp(LoginDao dao){
	 
	 logindao=dao;
};

	@Override
	public User signIn(String userName, String password, String code) {
		
		return logindao.signIn(userName, password, code);
	}
	
	@Override
	public User adminSignIn(String userName, String password) {
		
		return logindao.adminSignIn(userName, password);
	}

	@Override
	public boolean signOut(String userName) {
		// TODO Auto-generated method stub
		return false;
	}

 
 

}
