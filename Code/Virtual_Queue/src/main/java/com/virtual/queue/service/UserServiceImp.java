package com.virtual.queue.service;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.virtual.queue.beans.User;
import com.virtual.queue.dao.QueueDao;
import com.virtual.queue.dao.ResetPasswordDao;
import com.virtual.queue.dao.UserDao;
import com.virtual.queue.request.UserPasswordResetRequest;
import com.virtual.queue.request.UserRequest;

@Service
public class UserServiceImp implements UserService {

	@Autowired
	private UserDao userDao;
	@Autowired
	private ResetPasswordDao resetPassDao;

	@Autowired
	QueueDao queueDao;
	public UserServiceImp() {
	}

	public UserServiceImp(UserDao userD, QueueDao queueD,
			ResetPasswordDao rspsswD) {
		userDao = userD;
		queueDao = queueD;
		resetPassDao = rspsswD;

	}

	@Override
	public User getUser(String username, String passwd) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getCurrentlyAuthenticatedUserName() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public User getUserByToken(String token) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<User> getAll() {

		return userDao.getAll();
	}

	@Override
	public void addUser(User user) {
		try {
			userDao.addUser(user);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

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
		// TODO Auto-generated method stub
		return null;
	}
	
	public User getUserById(Long userId) {
		
		return userDao.getUserToModify(userId);
	}

	@Override
	public void resetPassword(UserPasswordResetRequest passwordReset)
			throws Exception {

		resetPassDao.resetPassword(passwordReset.getUserName(),
				passwordReset.getSecurityAnswer(),
				passwordReset.getSecurityQuestion(),
				passwordReset.getNewPassword());

	}
	
	@Override
	public void editUserInfo(UserRequest editInfo) throws Exception {

		 userDao.editUserById(editInfo.getFirstName(), editInfo.getLastName(), 
				 editInfo.getEmail(), editInfo.getNewPassword(),
				 editInfo.getUserName(), editInfo.getSecurityAnswer(), editInfo.getSecurityQuestion(),
				editInfo.getCell(), editInfo.getAge(), editInfo.getCode());

	}
	
	

	@Override
	public String storeToken(long userId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean removeUserFromQueue(long rideId, long userId) {

		return queueDao.removeUserFromQueue(rideId, userId);
	}

	@Override
	public boolean enabledDisabledUser(long userId, String flag ) {

		return userDao.enabledDisabledUser(userId, flag);
	}
	

}
