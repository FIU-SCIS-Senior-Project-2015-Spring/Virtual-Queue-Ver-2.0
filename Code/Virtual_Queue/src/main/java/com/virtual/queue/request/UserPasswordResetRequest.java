package com.virtual.queue.request;

import java.io.Serializable;

public class UserPasswordResetRequest implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8916978909887885460L;
	/**
	 * 
	 */

	private String userName;
	private String securityQuestion;
	private String securityAnswer;
	private String newPassword;
	private String confirmNewPassword;

	public UserPasswordResetRequest() {
	}

	public String getConfirmNewPassword() {
		return confirmNewPassword;
	}

	public void setConfirmNewPassword(String confirmNewPassword) {
		this.confirmNewPassword = confirmNewPassword;
	}

	public String getSecurityQuestion() {
		return securityQuestion;
	}

	public void setSecurityQuestion(String securityQuestion) {
		this.securityQuestion = securityQuestion;
	}

	public String getSecurityAnswer() {
		return securityAnswer;
	}

	public void setSecurityAnswer(String securityAnswer) {
		this.securityAnswer = securityAnswer;
	}

	public String getNewPassword() {
		return newPassword;
	}

	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

}
