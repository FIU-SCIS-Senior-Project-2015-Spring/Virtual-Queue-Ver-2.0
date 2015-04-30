package com.test.spring;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.test.spring.mockups.LoginDaoImpMock;
import com.virtual.queue.beans.User;
import com.virtual.queue.dao.LoginDao;
import com.virtual.queue.service.LoginService;
import com.virtual.queue.service.LoginServiceImp;

public class LoginServiceImpTest  {
    LoginDao dao;
    LoginService service;
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		
		dao=new LoginDaoImpMock();
	  service= new LoginServiceImp(dao);
	
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testSignIn() {
	User user=	service.signIn("test", "passwrod", "code");
	assertNotNull(user);
	assertEquals(user.getUserName(), user.getDemoUser().getUserName());
	
	
	}

	@Test
	public void testSignOut() {
	 //not implemented
	}

}
