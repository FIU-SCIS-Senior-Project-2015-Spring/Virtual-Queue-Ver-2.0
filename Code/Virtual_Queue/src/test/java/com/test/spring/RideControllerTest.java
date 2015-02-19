package com.test.spring;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.test.spring.mockups.RideServiceImpMock;
import com.virtual.queue.beans.Ride;
import com.virtual.queue.beans.RideInfo;
import com.virtual.queue.beans.User;
import com.virtual.queue.controller.RideController;
import com.virtual.queue.service.RideService;

public class RideControllerTest {

	RideController controller;
	RideService service;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		service = new RideServiceImpMock();
		controller = new RideController(service);
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public final void testGetAllRides() {

		List<RideInfo> list = controller.getAllRides();
		assertNotNull(list);
		assertNotSame(0, list.size());
		assertEquals("RideName", list.get(0).getrName());

	}

	@Test
	public final void testGetRideByUser() {

		List<RideInfo> ride = controller.getRideByUser(1l);
		assertNotNull(ride);
		assertNotSame(0, ride.size());
		assertEquals("RideName", ride.get(0).getrName());

	}

	@Test
	public final void testGetRideByUserID() {
		List<RideInfo> ride = controller.getRideByUser(1l);
		assertNotNull(ride);
		assertNotSame(0, ride.size());
		assertEquals("RideName", ride.get(0).getrName());
	}

	@Test
	public final void testAddRide() {

		Ride ride = new Ride();
		ride.setRideId(1L);
		ride.setrName("Ride");

		User user = new User();
		user.setUserid(1L);
		user.setUserName("username");

		controller.addRide(ride, user);
	}

	@Test
	public final void testUpdateRide() {

		Ride ride = new Ride();
		ride.setRideId(1L);
		ride.setrName("Ride"); 
		controller.updateRide(ride);

	}

	@Test
	public final void testRemoveRideByUser() {

		controller.removeRideByUser(1L, 1L);
	}

	@Test
	public final void testRemoveRideByUserID() {

		controller.removeRideByUserID(1L, 1L);

	}

	@Test
	public final void testAddrideByUserGet() {

	}

	@Test
	public final void testAddrideByUser() {
		controller.addrideByUser(1L, 1L);
	}

	@Test
	public final void testRemoveRideById() {
		controller.removeRideById("1L");
	}

	 

}
