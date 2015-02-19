package com.virtual.queue.service;

import java.util.List;

import com.virtual.queue.beans.UserQueueInfo;

public interface NotificationService {
	public List<UserQueueInfo> pullNotInfo(long rideId);
	public List<UserQueueInfo> pullAllNotInfo();
	public void notifyUser(long rideId) throws Exception;
	public void notifyAllUsers() throws Exception;
}
