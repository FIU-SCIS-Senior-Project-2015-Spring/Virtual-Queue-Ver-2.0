package com.virtual.queue.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.virtual.queue.dao.QueueDao;
import com.virtual.queue.dao.QueueDaoImp;

@Service
public class QueueServiceImp implements QueueService {
	@Autowired
	QueueDao queuDao;

	@Override
	public boolean removeUserFromQueue(long rideId, long userid) {
		return new QueueDaoImp().removeUserFromQueue(rideId, userid);
	}

	@Override
	public boolean removeAllUsersFromQueue(long rideId) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<Long> getUsersFromQueue(long rideId, int lenght) {

		return new QueueDaoImp().getAllUserQueueForRide(rideId, lenght);

	}

}
