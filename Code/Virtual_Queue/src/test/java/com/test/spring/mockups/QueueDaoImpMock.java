package com.test.spring.mockups;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import com.virtual.queue.beans.QueueInfo;
import com.virtual.queue.beans.RideInfo;
import com.virtual.queue.beans.User;
import com.virtual.queue.beans.UserQueueInfo;
import com.virtual.queue.dao.QueueDao;

public class QueueDaoImpMock implements QueueDao {

	@Override
	public List<UserQueueInfo> pullInfo(long rideId) {

		if (rideId == 0)
			return null;
		List<UserQueueInfo> list = new ArrayList<UserQueueInfo>();
		UserQueueInfo info = new UserQueueInfo();
		info.setRideName("RideName");
		list.add(info);

		return list;

	}

	@Override
	public List<UserQueueInfo> pullAllInfo() {
		List<UserQueueInfo> list = new ArrayList<UserQueueInfo>();
		UserQueueInfo info = new UserQueueInfo();
		info.setRideName("RideName");
		list.add(info);

		return list;
	}

	@Override
	public LinkedList<User> getAllUserQueueForRide(long rideId) {

		if (rideId == 0)
			return null;
		LinkedList<User> list = new LinkedList<User>();
		list.add(User.getDemoUser());
		return list;

	}

	@Override
	public QueueInfo getQueueInfoByRideId(long rideId) {
		// TODO Auto-generated method stub
		if (rideId == 0)
			return null;

		QueueInfo info = new QueueInfo();
		info.setQueueId(1);

		return info;
	}

	@Override
	public boolean removeUserFromQueue(long rideId, long userid) {
		if (rideId == 0 || userid == 0)
			return false;
		return true;
	}

	@Override
	public boolean removeAllUsersFromQueue(long rideId) {
		if (rideId == 0)
			return false;
		return true;
	}

	@Override
	public LinkedList<RideInfo> getRideListByUser(long userId) throws Exception {

		LinkedList<RideInfo> list = new LinkedList<RideInfo>();
		RideInfo info = new RideInfo();
		info.setrName("RideName");
		list.add(info);

		RideInfo info1 = new RideInfo();
		info1.setrName("RideName");
		list.add(info1);

		return list;
	}

	@Override
	public List<Long> getUserToRemoveFromQueue(Long rideId) {
		List<Long> list = new ArrayList<Long>();
		list.add(1L);

		return list;
	}

	@Override
	public LinkedList<Long> getAllUserQueueForRide(long rideId, int interval) {
		LinkedList<Long> list = new LinkedList<Long>();
		list.add(1L);
		list.add(2L);
		return list;
	}

}
