package com.virtual.queue.rule;

import java.math.BigDecimal;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.virtual.queue.beans.Coordinate;
import com.virtual.queue.beans.QueueInfo;
import com.virtual.queue.beans.RideInfo;
import com.virtual.queue.beans.RuleCapacityBean;
import com.virtual.queue.beans.User;
import com.virtual.queue.dao.QueueDao;
import com.virtual.queue.dao.QueueDaoImp;
import com.virtual.queue.dao.RideDao;
import com.virtual.queue.dao.RideDaoImp;
import com.virtual.queue.exception.NotificationException;
import com.virtual.queue.service.RuleService;
import com.virtual.queue.service.RuleServiceImp;
import com.virtual.queue.utility.QueueUtil;

//calculate waiting time (when no other ride exists) by taking all
// people queued for that ride and divide it by ride_capacity
// (flooring ex: 50 people queued already/20 can ride simultaneosly=
// 2.5. so, 20 min waiting time) for that specific ride.
// calculate ceiling also (ceiling ex: 50people queued already/20 can
// ride simultaneosly= 2.5. so, 30 min //waiting time (when other rides
// exists) for that specific ride.
// NOTE: take ceiling and flooring number per each ride added.
// Then, to add other ride when user has more rides on his account, take
// the ceiling number (in this case 30 min) and ADD walking distance
// calculating longitude and latitude or other way.
// AND ADD + all other ceiling waiting times for other rides and add
// them all
// THEN, compare ride flooring waiting time for ride user is trying to
// add with the SUM of all ceiling calculated before and make sure the
// new ride flooring time is greater..so, ride can be added
// also, check park is not near closing and user hasn't already queued
// for too many rides
@Component
public class AddUserRule implements Rule {

	private final static double WALKING_SPEED_MIN = 0.052;

	private RideInfo ride = null;
	private QueueInfo queueInfo = null;
	private LinkedList<RideInfo> rideList = null;
	private List<User> userList = null;

	public void loadData(long userId, long rideId) throws Exception {
		// take all rides for that user. using rideDao.
		// select the last ride registered for that user.
		QueueDao qDao = new QueueDaoImp();
		RideDao rDao = new RideDaoImp();
		try {

			ride = rDao.getRideById(rideId);

		} catch (NotificationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		queueInfo = qDao.getQueueInfoByRideId(rideId);
		// if not ride added..Add the new ride and get the waiting time for that
		// user
		// rDao.getRideByUser(userId)
		// get all rides that user is registered for.

		rideList = qDao.getRideListByUser(userId);

		// get all user for that queue.
		userList = qDao.getAllUserQueueForRide(rideId);
		// calculate waiting time (when no other ride exists) by taking all
		// people queued for that ride and divide it by ride_capacity
		// (flooring ex: 50 people queued already/20 can ride simultaneosly=
		// 2.5. so, 20 min waiting time) for that specific ride.
		// calculate ceiling also (ceiling ex: 50people queued already/20 can
		// ride simultaneosly= 2.5. so, 30 min //waiting time (when other rides
		// exists) for that specific ride.
		// NOTE: take ceiling and flooring number per each ride added.
		// Then, to add other ride when user has more rides on his account, take
		// the ceiling number (in this case 30 min) and ADD walking distance
		// calculating longitude and latitude or other way.
		// AND ADD + all other ceiling waiting times for other rides and add
		// them all
		// THEN, compare ride flooring waiting time for ride user is trying to
		// add with the SUM of all ceiling calculated before and make sure the
		// new ride flooring time is greater..so, ride can be added
		// also, check park is not near closing and user hasn't already queued
		// for too many rides

	};

	@Override
	public RuleCapacityBean loadDataRule(long userId, long rideId)
			throws Exception {
		// TODO:use injection.
		RuleService service = new RuleServiceImp();
		return service.loadDataRule(userId, rideId);
	}

	@Override
	public boolean apply() {
		int waitingTime = 0;
		int rideCap = 0;
		int duration = 0;
		int totalWtime = 0;

		// get ride capacity
		rideCap = ride.getCapacity();
		// get ride duration
		duration = ride.getInterval();
		QueueDao qDao = new QueueDaoImp();

		if (userList == null)
			return false;// TODO:this needs to return custom message so the
							// caller knows the problem.

		// queue already full return false;
		if (userList.size() == queueInfo.getCapacity()) {
			return false;
		}
		// user already has been registered for other rides.
		if (rideList != null && !rideList.isEmpty()) {

			ListIterator<RideInfo> listIterator = rideList.listIterator();
			RideInfo rinfo = null;
			while (listIterator.hasNext()) {

				rinfo = listIterator.next();
				int size = qDao.getAllUserQueueForRide(rinfo.getRideId())
						.size();
				int wtime = QueueUtil.getWaitingTime(size, rinfo.getCapacity(),
						rinfo.getInterval(), false);
				totalWtime += wtime;// all all waiting time from each ride

			}

			RideInfo lastRide = rideList.getLast();
			QueueUtil util = new QueueUtil();
			Coordinate rideCoor = ride.getCoordinate();
			Coordinate lastRideCoor = lastRide.getCoordinate();
			Double wDistance = new Double("0.0");

			if (rideCoor != null && lastRideCoor != null) {
				wDistance = util.calculateDistance(rideCoor.getLatitude()
						.doubleValue(), rideCoor.getLongitude().doubleValue(),
						lastRideCoor.getLatitude().doubleValue(), lastRideCoor
								.getLongitude().doubleValue());
			}
			Double temp = wDistance / WALKING_SPEED_MIN;

			Double wtime = Math.ceil(temp);

			Double finaltime = totalWtime + wtime;

			int count = userList.size();
			// get previous ride.
			waitingTime = QueueUtil.getWaitingTime(count, rideCap, duration,
					true);

			// check waiting time.
			if (waitingTime < finaltime) {
				return false;
			}

		}

		return true;

	}

	@SuppressWarnings("unused")
	private void ValidationLogic() {

	}

	@Override
	public void loadData() {
		// TODO Auto-generated method stub

	}

}
