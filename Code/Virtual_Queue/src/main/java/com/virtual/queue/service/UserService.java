package com.virtual.queue.service;

import java.util.List;

import com.virtual.queue.beans.User;
import com.virtual.queue.request.UserPasswordResetRequest;
import com.virtual.queue.request.UserRequest;

 

public interface UserService {
	 
		public User getUser(String username, String passwd); 
		public String getCurrentlyAuthenticatedUserName(); 
		public User getUserByToken(String token);
		public String storeToken(long userId);
        public List<User> getAll(); 
        public void addUser(User user);
		public void updateUser(User user);
		public void deleteUserById(Long id);
		public User getUserByUserName(String userName);
		public void resetPassword(UserPasswordResetRequest passwordReset) throws Exception;
		boolean removeUserFromQueue(long rideId, long userId);
		public User getUserById(Long userId);
		void editUserInfo(UserRequest editInfo) throws Exception;
		boolean enabledDisabledUser(long userId, String flag);
}
