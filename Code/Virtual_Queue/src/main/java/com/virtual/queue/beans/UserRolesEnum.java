package com.virtual.queue.beans;

public enum UserRolesEnum {

	Admin("Administrator"), Registered("Registered"), Guest("Guest"),Empty("");
	private String role;

	UserRolesEnum(String p) {
		role = p;
	}

	String getPrice() {
		return role;
	}

}
