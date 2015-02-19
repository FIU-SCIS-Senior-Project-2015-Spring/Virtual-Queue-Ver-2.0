package com.virtual.queue.dao;

import java.util.List;

import com.virtual.queue.beans.RideInfo;
import com.virtual.queue.exception.NotificationException;

public interface RideDao {
	public List<RideInfo> pullRideInfo() throws NotificationException ;
	public List<RideInfo> getRideByUser(Long userId) throws NotificationException;
	public RideInfo getRideById(long rideId) throws NotificationException;
	public boolean addUserRideById(Long rideId, Long userId);
	public List<RideInfo> getAll();
	
	
	
}
