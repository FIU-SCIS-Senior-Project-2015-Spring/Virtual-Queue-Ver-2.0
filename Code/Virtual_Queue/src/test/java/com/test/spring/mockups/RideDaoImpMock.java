package com.test.spring.mockups;

import java.util.ArrayList;
import java.util.List;

import com.virtual.queue.beans.RideInfo;
import com.virtual.queue.dao.RideDao;
import com.virtual.queue.exception.NotificationException;

public class RideDaoImpMock implements RideDao {

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
	public List<RideInfo> getRideByUser(Long userId)
			throws NotificationException {
		List<RideInfo> list = new ArrayList<RideInfo>();
		RideInfo info1 = new RideInfo();
		info1.setrName("RideName");
		info1.setRideId(1);
		list.add(info1);

		return list;
	}

	@Override
	public RideInfo getRideById(long rideId) throws NotificationException {
		RideInfo info1 = new RideInfo();
		info1.setrName("RideName");
		info1.setRideId(1);
		return info1;
	}

	@Override
	public boolean addUserRideById(Long rideId, Long userId) {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public List<RideInfo> getAll() {
		List<RideInfo> list = new ArrayList<RideInfo>();
		RideInfo info1 = new RideInfo();
		info1.setrName("RideName");
		info1.setRideId(1);
		list.add(info1);
		return list;
	}

}
