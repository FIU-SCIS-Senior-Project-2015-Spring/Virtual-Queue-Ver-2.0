package com.virtual.queue.service;

import java.util.List;

 

public interface QueueService {
public boolean removeUserFromQueue(long rideId,long userid);
public boolean removeAllUsersFromQueue(long rideId);
public List<Long> getUsersFromQueue(long rideId,int lenght);
	
	 
}
