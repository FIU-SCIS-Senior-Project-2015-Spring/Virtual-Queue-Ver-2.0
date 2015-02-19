package com.test.spring;

import static org.junit.Assert.*;

import java.util.List; 
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test; 
import com.test.spring.mockups.UserServiceImpMock;
import com.virtual.queue.beans.User;
import com.virtual.queue.controller.UserController;
import com.virtual.queue.request.UserPasswordResetRequest;
import com.virtual.queue.service.UserService;

public class UserControllerTest {
	
	UserController controller;
	UserService service;
	UserPasswordResetRequest psswRequest;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {

		service = new UserServiceImpMock();
		controller = new UserController(service);
		psswRequest = new UserPasswordResetRequest();
		psswRequest.setNewPassword("newPassword");
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testGetAllUsers() {
		List<User> list = controller.getAllUsers();
		assertNotNull(list);
		assertEquals(1, list.size());
		assertEquals("FirstName", list.get(0).getFirstName());
	}

	@Test
	public void testAddUser() {

		controller.addUser(User.getDemoUser());

	}

	@Test
	public void testUpdateUser() {
		controller.updateUser(User.getDemoUser());
	}

	@Test
	public void testRemoveUser() {
		controller.removeUser(1L);
	}

	@Test
	public void testGetUserByUserName() {
		User user = controller.getUserByUserName("username");
		assertNotNull(user);
		assertEquals("FirstName", user.getFirstName());
	}

	@Test
	public void testSavePerson() {
		Boolean result = controller.savePerson(User.getDemoUser());

		assertEquals(true, result);
	}

	@Test
	public void testResetPassword() {

		Boolean result = controller.resetPassword(psswRequest);
		assertEquals(true, result);

	}

}
