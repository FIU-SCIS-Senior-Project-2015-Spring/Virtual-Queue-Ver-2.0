package com.virtual.queue.service;

import java.util.List;

import com.virtual.queue.beans.NotificationInfo;
import com.virtual.queue.beans.Ride;
import com.virtual.queue.beans.RideInfo;
import com.virtual.queue.beans.RuleCapacityBean;
import com.virtual.queue.beans.User;
import com.virtual.queue.builder.RuleBuilderImp;
import com.virtual.queue.dao.QueueDao;
import com.virtual.queue.dao.RideDao;
import com.virtual.queue.dao.UserDao;
import com.virtual.queue.exception.NotificationException;
import com.virtual.queue.handler.EmailNotificationHandlerImp;
import com.virtual.queue.handler.NotificationHandler;
import com.virtual.queue.rule.Rule;
import com.virtual.queue.utility.QueueUtil;
import com.virtual.queue.validator.Validator;
import com.virtual.queue.validator.ValidatorFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RideServiceImp implements RideService {
	@Autowired
	RideDao rideDao;

	@Autowired
	QueueDao queueDao;

	@Autowired
	RuleService ruleService;

	@Autowired
	UserDao userDao;
public RideServiceImp(){}
	
	public RideServiceImp(RideDao rideD,QueueDao queueD,UserDao userD){
		rideDao=rideD;
		queueDao=queueD;
		userDao=userD;
		 
	}

	@Override
	public List<RideInfo> getAll() throws Exception {

		List<RideInfo> list = rideDao.getAll();

		for (RideInfo info : list) {
			// get all data to calculate waiting time.

//			int count = queueDao.getAllUserQueueForRide(info.getRideId())
//					.size();
			int count = queueDao.getListRideInQueue(info.getRideId()).size();
			int capacity = info.getCapacity();
			int interval = info.getInterval();
			info.setTotalRecord(count);
			//long waitingTime = QueueUtil.getWaitingTime(count, capacity,
					//interval, true);
			long waitingTime = QueueUtil.getWaitingTime2(count, capacity, interval, info.getTimePerEvent(), info.getEntryTime(), info.getExitTime() , true);
			
			//long waitingTime = 5;
			info.setWaitingTime(waitingTime);

		}

		return list;
	}

	@Override
	public void addRide(Ride ride) {
		// TODO Auto-generated method stub

	}
	@Override
	public void removeFromFront(Long rideId) {
		queueDao.removeFromFront(rideId);

	}

	@Override
	public void updateRide(Ride ride) {
		// TODO Auto-generated method stub

	}

	@Override
	public void deleteRideById(Long id, Long userid) {

		queueDao.removeUserFromQueue(id, userid);

	}

	@Override
	public boolean addUserRideById(Long rideId, Long userid) throws Exception {

		Validator validator = new ValidatorFactory().getRideValidator();
		boolean result = false;

		// RuleBuilder builder = new RuleBuilderImp();
		List<Rule> rules = new RuleBuilderImp().buildRules();

		validator.setRules(rules);

		//if (validator.validate(userid, rideId)) {
		if (true) {
			// add user to ride/queue
			RideInfo info = rideDao.getRideById(rideId);
			int count = queueDao.getListRideInQueue(info.getRideId()).size();
			int capacity = info.getCapacity();
			int interval = info.getInterval();
			info.setTotalRecord(count);
			//long waitingTime = QueueUtil.getWaitingTime(count, capacity,
					//interval, true);
			long waitingTime = QueueUtil.getWaitingTime2(count, capacity, interval, info.getTimePerEvent(), info.getEntryTime(), info.getExitTime() , true);
			
				result = rideDao.addUserRideById(rideId, userid, waitingTime);
			/*
			 * check queue size and ride capacity. if there is no user on the
			 * queue yet, or the amount of users is less than the ride
			 * capacity,then notify this user just after add him/her to this
			 * queue.
			 */
				
			List<User> users = queueDao.getAllUserQueueForRide(rideId);
			
			User user = userDao.getUserById(userid);
			
			//RideInfo info = rideDao.getRideById(rideId);
			
			

			if (users != null && info != null && user!=null) {
				// Check for biz rules
				if (users.size() <= info.getCapacity()) {

					NotificationHandler handler = new EmailNotificationHandlerImp();
					NotificationInfo notInfo = new NotificationInfo();
					// set data
					notInfo.setEmail(user.getEmail());
					notInfo.setName(user.getFirstName() + " , "
							+ user.getLastName());
					notInfo.setEmail(user.getEmail());

					double timeMin = info.getInterval() / 60.0;
					notInfo.setMessage("Your "
							+ info.getrName()
							+ " ride is due in :"
							+ timeMin
							+ "minute/s, Please, try to be on time and Enjoy your Ride :) ");
					// notify user
					handler.notifiyUser(notInfo);

				}

			}

		}
		return result;

	}

	@Override
	public boolean removeRidebyId(String id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<RideInfo> pullRideInfo() throws NotificationException {

		return rideDao.pullRideInfo();

	}

	@Override
	public RideInfo getRidebyId(long rideId) throws NotificationException {
		return rideDao.getRideById(rideId);
	}

	@Override
	public List<RideInfo> getRidesByUser(long userId) throws Exception {

		List<RideInfo> list = rideDao.getRideByUser(userId);
		RuleCapacityBean bean = null;

		for (RideInfo info : list) {
			// get all data to calculate waiting time.
			bean = ruleService.loadDataRule(userId, info.getRideId());
			 
			long queueId =  queueDao.getUserQueueId(userId, info.getRideId());
			//Long queueId = bean.getQueueInfo().getQueueRealId();
			int numFront = queueDao.getNumFrontUser(info.getRideId(), queueId);
		
			int capacity = info.getCapacity();
			int interval = info.getInterval();
			
			long waitingTime = QueueUtil.getDynWaitingTime(numFront, capacity,
					bean.getRide().getInterval(), bean.getRide().getTimePerEvent(), bean.getRide().getEntryTime(), bean.getRide().getExitTime() , true);
			// waitingTime = 5;
			//info.setWaitingTime(waitingTime);
			info.setInterval((int)waitingTime);
			info.setInFront(numFront);
			//bean.getRide().setInterval((int)waitingTime);
			

		}

		return list;

	}
	
	public void editRide(Long activityNum, String rideName, Long timePerEvent, Long entryTime, Long exitTime, Long maxCpty,  Long concRide){
		rideDao.editRide(activityNum, rideName, timePerEvent, entryTime, exitTime, maxCpty, concRide);
	}

}
