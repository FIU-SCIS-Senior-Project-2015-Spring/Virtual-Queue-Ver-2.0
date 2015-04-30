package com.virtual.queue.dao;

import java.util.LinkedList;

import com.virtual.queue.beans.User;

public interface AdminDao {
	
	public LinkedList<User> getAllUsers();

}
