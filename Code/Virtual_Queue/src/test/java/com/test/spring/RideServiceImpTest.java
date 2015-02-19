/**
 * 
 */
package com.test.spring;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.test.spring.mockups.QueueDaoImpMock;
import com.test.spring.mockups.RideDaoImpMock;
import com.test.spring.mockups.UserDaoImpMock;
import com.virtual.queue.beans.Ride;
import com.virtual.queue.beans.RideInfo;
import com.virtual.queue.dao.QueueDao;
import com.virtual.queue.dao.RideDao;
import com.virtual.queue.dao.UserDao;
import com.virtual.queue.exception.NotificationException;
import com.virtual.queue.service.RideService;
import com.virtual.queue.service.RideServiceImp;

/**
 * @author SoftwareDevelopment
 *
 */
public class RideServiceImpTest {

	RideService service;
	RideDao rDao;
	QueueDao qDao;
	UserDao uDao;
	Ride ride;

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	/**
	 * @throws java.lang.Exception
	 */
	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		rDao = new RideDaoImpMock();
		qDao = new QueueDaoImpMock();
		uDao = new UserDaoImpMock();

		ride = new Ride();
		ride.setrName("RideName");
      service=new RideServiceImp( rDao,qDao,uDao);
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
	}

	/**
	 * Test method for {@link com.virtual.queue.service.RideServiceImp#getAll()}
	 * .
	 */
	@Test
	public void testGetAll() {
		try {

			List<RideInfo> list = service.getAll();
			assertNotNull(list);
			assertNotSame(0, list.size());
			assertEquals("RideName", list.get(0).getrName());

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * Test method for
	 * {@link com.virtual.queue.service.RideServiceImp#addRide(com.virtual.queue.beans.Ride)}
	 * .
	 */
	@Test
	public void testAddRide() {

		Ride ride = new Ride();
		ride.setrName("RideName");

		service.addRide(ride);

	}

	/**
	 * Test method for
	 * {@link com.virtual.queue.service.RideServiceImp#updateRide(com.virtual.queue.beans.Ride)}
	 * .
	 */
	@Test
	public void testUpdateRide() {

		service.updateRide(ride);
	}

	/**
	 * Test method for
	 * {@link com.virtual.queue.service.RideServiceImp#deleteRideById(java.lang.Long, java.lang.Long)}
	 * .
	 */
	@Test
	public void testDeleteRideById() {

		service.updateRide(ride);
	}

	/**
	 * Test method for
	 * {@link com.virtual.queue.service.RideServiceImp#addRideById(java.lang.Long, java.lang.Long)}
	 * .
	 */
	@Test
	public void testAddRideById() {

		try {
             //TODO:pending dependencies 
			//assertEquals(true, service.addUserRideById(1L, 1L));
            
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/**
	 * Test method for
	 * {@link com.virtual.queue.service.RideServiceImp#removeRidebyId(java.lang.String)}
	 * .
	 */
	@Test
	public void testRemoveRidebyId() {

		assertEquals(false, service.removeRidebyId("1"));

	}

	/**
	 * Test method for
	 * {@link com.virtual.queue.service.RideServiceImp#pullRideInfo()}.
	 */
	@Test
	public void testPullRideInfo() {

		List<RideInfo> list;
		try {
			list = service.pullRideInfo();

			assertNotNull(list);
			assertNotSame(0, list.size());

			assertEquals("RideName", list.get(0).getrName());
		} catch (NotificationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
