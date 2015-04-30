package com.test.spring;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.virtual.queue.dao.LoginDao;
import com.virtual.queue.dao.LoginDaoImp;

public class LoginDaoImpTest {
   LoginDao dao;
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		dao=new LoginDaoImp();
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testSignOut() {
		 
	}

	@Test
	public void testValidLogin() {
		 
	}

	@Test
	public void testSignIn() {
		 
	}

}
