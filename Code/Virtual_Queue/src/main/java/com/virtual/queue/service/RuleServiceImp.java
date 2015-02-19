package com.virtual.queue.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.virtual.queue.beans.RuleCapacityBean;
import com.virtual.queue.dao.QueueDao;
import com.virtual.queue.dao.RideDao;

@Service
public class RuleServiceImp implements RuleService {
	@Autowired
	QueueDao qDao;
	@Autowired
	RideDao rDao;

	@Override
	public RuleCapacityBean loadDataRule(long userId, long rideId)
			throws Exception {

		return new RuleCapacityBean(rDao.getRideById(rideId),
				qDao.getQueueInfoByRideId(rideId),
				qDao.getRideListByUser(userId),
				qDao.getAllUserQueueForRide(rideId));
	}

@Override
public RuleCapacityBean loadDataRuleAll( long rideId)
		throws Exception {

	return new RuleCapacityBean(rDao.getRideById(rideId),
			qDao.getQueueInfoByRideId(rideId),
			null,
			qDao.getAllUserQueueForRide(rideId));
}
}