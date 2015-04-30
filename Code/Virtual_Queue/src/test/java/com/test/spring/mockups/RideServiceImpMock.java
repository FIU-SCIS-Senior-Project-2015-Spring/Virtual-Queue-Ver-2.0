package com.test.spring.mockups;

import java.util.ArrayList;
import java.util.List; 
import com.virtual.queue.beans.Ride;
import com.virtual.queue.beans.RideInfo;
import com.virtual.queue.exception.NotificationException;
import com.virtual.queue.service.RideService;

public class RideServiceImpMock implements RideService {

	@Override
	public List<RideInfo> getAll() throws Exception {
		List<RideInfo> list = new ArrayList<RideInfo>();
		RideInfo info1 = new RideInfo();
		info1.setrName("RideName");
		info1.setRideId(1);
		list.add(info1);

		return list;
	}

	@Override
	public void addRide(Ride ride) {
		System.out.println("Ride Added");

	}

	@Override
	public void updateRide(Ride ride) {
		System.out.println("Ride Added");

	}

	@Override
	public void deleteRideById(Long id, Long userid) {

		System.out.println("Ride deleted");

	}

	@Override
	public boolean removeRidebyId(String id) {
		if (id != null)
			return true;
		return false;
	}

	@Override
	public boolean addUserRideById(Long rideId, Long userid) throws Exception {
		if (userid != null && rideId != null)
			return true;
		return false;
	}

	@Override
	public List<RideInfo> pullRideInfo() throws NotificationException {
		List<RideInfo> list = new ArrayList<RideInfo>();
		RideInfo info1 = new RideInfo();
		info1.setrName("RideName");
		info1.setRideId(1);
		list.add(info1);

		return list;
	}

	@Override
	public RideInfo getRidebyId(long rideId) throws NotificationException {
		RideInfo info1 = new RideInfo();
		info1.setrName("RideName");
		info1.setRideId(1);
		return info1;
	}

	@Override
	public List<RideInfo> getRidesByUser(long userId)
			throws NotificationException, Exception {

		if (userId == 0)
			return null;
		List<RideInfo> list = new ArrayList<RideInfo>();
		RideInfo info1 = new RideInfo();
		info1.setrName("RideName");
		info1.setRideId(1);
		list.add(info1);
		return list;
	}

}
