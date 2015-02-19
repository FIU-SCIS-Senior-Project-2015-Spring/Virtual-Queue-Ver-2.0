package com.virtual.queue.beans;

public class Role {

	String role_id;
	String role_type;
	String role_desc;
	boolean enable;

	public String getRole_id() {
		return role_id;
	}

	public void setRoleId(String role_id) {
		this.role_id = role_id;
	}

	public String getRoleType() {
		return role_type;
	}

	public void setRoleType(String role_type) {
		this.role_type = role_type;
	}

	public String getRoleDesc() {
		return role_desc;
	}

	public void setRoleDesc(String role_desc) {
		this.role_desc = role_desc;
	}

	public boolean isEnable() {
		return enable;
	}

	public void setEnable(boolean enable) {
		this.enable = enable;
	}

	public Role(String roleId, String roleType, boolean flag, String roleDesc) {
		role_type = roleType;
		role_id = roleId;
		role_desc = roleDesc;
		enable = flag;

	}

	public Role() {
		// TODO Auto-generated constructor stub
	}
}
