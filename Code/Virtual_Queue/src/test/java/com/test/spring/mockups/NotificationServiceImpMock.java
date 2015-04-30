package com.test.spring.mockups;

import java.util.ArrayList;
import java.util.List;

import com.virtual.queue.beans.UserQueueInfo;
import com.virtual.queue.service.NotificationService;

public class NotificationServiceImpMock implements NotificationService {

	@Override
	public List<UserQueueInfo> pullNotInfo(long rideId) {
		if (rideId > 0) {
			return null;
		}

		List<UserQueueInfo> list = new ArrayList<UserQueueInfo>();
		UserQueueInfo info = new UserQueueInfo();
		info.setEmail("test@test.com");
		list.add(info);
		return list;
	}

	@Override
	public List<UserQueueInfo> pullAllNotInfo() {

		List<UserQueueInfo> list = new ArrayList<UserQueueInfo>();
		UserQueueInfo info = new UserQueueInfo();
		info.setEmail("test@test.com");
		list.add(info);
		return list;
	}

	@Override
	public void notifyUser(long rideId) throws Exception {
		if(rideId==0)throw new Exception("invalid rideId");

	}

	@Override
	public void notifyAllUsers() throws Exception {
		System.out.println("notifyAllUser");

	}

}
