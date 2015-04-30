package com.virtual.queue.request;

import java.io.Serializable;

public class UserRequest implements Serializable {


	
	private static final long serialVersionUID = 8916978909887885460L;
	

	private String userName;
	private String firstName;
	private String lastName;
	private String email;
	private String securityQuestion;
	private String securityAnswer;
	private String password;
	private String age;
	private String phoneNumber;
	private String code;
	

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
		return password;
	}

	public void setNewPassword(String newPassword) {
		this.password = newPassword;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAge() {
		return age;
	}

	public void setAge(String age) {
		this.age = age;
	}

	public String getCell() {
		return phoneNumber;
	}

	public void setCell(String cell) {
		this.phoneNumber = cell;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

}
