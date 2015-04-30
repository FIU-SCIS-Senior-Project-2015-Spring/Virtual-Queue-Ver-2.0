package com.test.spring.mockups;

import com.virtual.queue.dao.ResetPasswordDao;

public class ResetPasswordDaoImpMock implements ResetPasswordDao {

	@Override
	public void resetPassword(String userName, String securityAnswer,
			String securityQuestion, String newPassword) throws Exception {
		if (userName == null || securityAnswer == null
				|| securityQuestion == null || newPassword == null)
			throw new Exception("invalid input");

	}

}
