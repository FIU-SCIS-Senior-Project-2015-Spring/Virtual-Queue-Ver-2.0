package com.test.spring;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.test.spring.mockups.QueueDaoImpMock;
import com.test.spring.mockups.ResetPasswordDaoImpMock;
import com.test.spring.mockups.UserDaoImpMock;
import com.virtual.queue.beans.User;
import com.virtual.queue.dao.QueueDao;
import com.virtual.queue.dao.ResetPasswordDao;
import com.virtual.queue.dao.UserDao;
import com.virtual.queue.request.UserPasswordResetRequest;
import com.virtual.queue.service.UserService;
import com.virtual.queue.service.UserServiceImp;

public class UserServiceImpTest {
	private UserDao userDao;

	private ResetPasswordDao resetPassDao;
	private QueueDao queueDao;
	UserService service;
	UserPasswordResetRequest passReset;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {

		userDao = new UserDaoImpMock();
		queueDao = new QueueDaoImpMock();
		resetPassDao = new ResetPasswordDaoImpMock(); 
		service = new UserServiceImp(userDao, queueDao, resetPassDao); 
		passReset=new UserPasswordResetRequest();
		passReset.setConfirmNewPassword("confirmation");
		passReset.setNewPassword("newPassword");
		passReset.setSecurityAnswer("securityQ");
		passReset.setSecurityQuestion("securityQuestion");
		passReset.setUserName("username");
		
	}

	@After
	public void tearDown() throws Exception {
	}


	@Test
	public void testGetAll() {
	List<User>	list=service.getAll();
	assertNotNull(list);
	assertNotSame(0, list.size());
	
	
	}

	@Test
	public void testAddUser() {
		
		
		service.addUser(User.getDemoUser());
	}

	@Test
	public void testUpdateUser() {
		service.updateUser(User.getDemoUser());
	}

	@Test
	public void testDeleteUserById() {
		service.deleteUserById(1L);
	}

	

	@Test
	public void testResetPassword() { 
		
		
		try {
			
			service.resetPassword(passReset);
		
		
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
