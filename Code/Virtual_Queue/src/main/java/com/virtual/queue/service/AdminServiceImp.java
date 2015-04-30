package com.virtual.queue.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.virtual.queue.beans.User;
import com.virtual.queue.dao.AdminDao;


@Service
public class AdminServiceImp implements AdminService {
	
	@Autowired
	AdminDao adminDao;
	
	@Override
	public List<User> getAll() throws Exception {
		
		
		List<User> list = adminDao.getAllUsers();
		

		return list;
	}

}
