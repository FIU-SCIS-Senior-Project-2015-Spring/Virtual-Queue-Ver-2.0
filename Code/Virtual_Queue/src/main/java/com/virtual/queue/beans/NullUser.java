package com.virtual.queue.beans;

public class NullUser extends AbstractUser {

	@Override
	public String getCode() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return "";
	}

	@Override
	public String getEmail() {
		// TODO Auto-generated method stub
		return "";
	}

	@Override
	public String getSecurityAnswer() {
		// TODO Auto-generated method stub
		return "";
	}

	@Override
	public String getPhoneNumber() {
		// TODO Auto-generated method stub
		return "";
	}

	@Override
	public Role getUserRole() {
		// TODO Auto-generated method stub
		return new Role();
	}

	@Override
	public Boolean isNill() {
		// TODO Auto-generated method stub
		return true;
	}

}
