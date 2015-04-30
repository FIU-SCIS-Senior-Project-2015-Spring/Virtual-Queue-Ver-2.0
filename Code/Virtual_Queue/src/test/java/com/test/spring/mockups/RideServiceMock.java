package com.test.spring.mockups;

import java.util.ArrayList;
import java.util.List;

import com.virtual.queue.beans.Ride;
import com.virtual.queue.beans.RideInfo;
import com.virtual.queue.exception.NotificationException;
import com.virtual.queue.service.RideService;

public class RideServiceMock implements RideService {

	@Override
	public List<RideInfo> getAll() throws Exception {
		List<RideInfo> list= new ArrayList<RideInfo>();
		RideInfo info= new RideInfo();
		info.setRideId(1L);
		info.setrName("Ride"); 
		list.add(info);
		
		return list;
	}

	@Override
	public void addRide(Ride ride) {
		// TODO Auto-generated method stub

	}

	@Override
	public void updateRide(Ride ride) {
		// TODO Auto-generated method stub

	}

	@Override
	public void deleteRideById(Long id, Long userid) {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean removeRidebyId(String id) {
		if(id ==null)return false;
		return true;
	}

	@Override
	public boolean addUserRideById(Long rideId, Long userid) throws Exception {
		 if(rideId ==null||userid==null){
			 
			 return false;
		 }
		
		
		return true;
	}

	@Override
	public List<RideInfo> pullRideInfo() throws NotificationException {
		 
			List<RideInfo> list= new ArrayList<RideInfo>();
			RideInfo info= new RideInfo();
			info.setRideId(1L);
			info.setrName("Ride"); 
			list.add(info);
			
			return list;
	}

	@Override
	public RideInfo getRidebyId(long rideId) throws NotificationException {
		RideInfo info= new RideInfo();
		info.setRideId(1L);
		info.setrName("Ride");
		return info;
	}

	@Override
	public List<RideInfo> getRidesByUser(long userId)
			throws NotificationException, Exception {
		if(userId ==0)throw new Exception("invalid input");
		
		
		List<RideInfo> list= new ArrayList<RideInfo>();
		RideInfo info= new RideInfo();
		info.setRideId(1L);
		info.setrName("Ride"); 
		list.add(info);
		
		return list;
	}

}
