package com.virtual.queue.dao;

public interface ResetPasswordDao {
	public void resetPassword(String userName, String securityAnswer,
			String securityQuestion, String newPassword) throws Exception ;

	
}
