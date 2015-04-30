package com.virtual.queue.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.virtual.queue.beans.Ride;
import com.virtual.queue.beans.RideInfo;
import com.virtual.queue.beans.User;
import com.virtual.queue.exception.NotificationException;

public interface RideService {

	public List<RideInfo> getAll() throws Exception;
	public void addRide(Ride ride);
	public void updateRide(Ride ride); 
	public void deleteRideById(Long id, Long userid);
	public boolean removeRidebyId(String id);
	public boolean addUserRideById(Long rideId, Long userid, boolean singleUser) throws Exception;
	public List<RideInfo> pullRideInfo() throws NotificationException;
	public RideInfo getRidebyId(long rideId) throws NotificationException;
	public List<RideInfo> getRidesByUser(long userId) throws NotificationException, Exception;
	public void removeFromFront(Long rideId) throws Exception ; //Added to remove from front of the Queue for a single ride
	public void editRide(Long activityNum, String rideName, Long timePerEvent, Long entryTime, Long exitTime, Long maxCpty,  Long concRide);
	public void setUpToSendNotification(Long rideId) throws Exception;
	

}
