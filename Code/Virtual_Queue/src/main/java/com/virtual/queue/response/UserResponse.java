package com.virtual.queue.response;

import com.virtual.queue.beans.AbstractUser;

public class UserResponse {
	private AbstractUser user;
	private String message;

	public UserResponse(AbstractUser userParm,String messageParm){
		
	 user=userParm;
	 message=messageParm;
		
	}
	
	
	public AbstractUser getUser() {
		return user;
	}

	public void setUser(AbstractUser user) {
		this.user = user;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
