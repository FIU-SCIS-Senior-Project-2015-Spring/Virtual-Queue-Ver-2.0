package com.virtual.queue.dao;

import java.util.LinkedList;
import java.util.List;

import com.virtual.queue.beans.QueueInfo;
import com.virtual.queue.beans.UserQueueInfo;
import com.virtual.queue.beans.RideInfo;
import com.virtual.queue.beans.User;
import com.virtual.queue.beans.Queue;

public interface QueueDao {
	public List<UserQueueInfo> pullInfo(long rideId);

	public List<UserQueueInfo> pullAllInfo(); 

	public LinkedList<User> getAllUserQueueForRide(long rideId);

	public QueueInfo getQueueInfoByRideId(long rideId);

	public boolean removeUserFromQueue(long rideId, long userid);

	public boolean removeAllUsersFromQueue(long rideId);

	public LinkedList<RideInfo> getRideListByUser(long userId) throws Exception;
	
	public List<Long> getUserToRemoveFromQueue(Long rideId);

	LinkedList<Long> getAllUserQueueForRide(long rideId, int interval);

	LinkedList<Queue> getListRideInQueue(long rideId);
	
	public boolean removeFromFront(long rideId); // Calls dataBase to remove from front of the queue
	
	public int getNumFrontUser(long rideId, long queueId); // calls database to get total number of visitors waiting infront of the specific visitor in a ride
	
	public long getUserQueueId(long userId, long rideId); // calls database to get queueId for specific user and ride
	
	public List<UserQueueInfo> getUserUpToPosition (long rideId, int position); // gets users information from 1 up to that position
	
	//public void readytoSendNotification(long rideId, int position); //getting visitor information within the queue, to send the notification. When we get proper information then we send notification
}
