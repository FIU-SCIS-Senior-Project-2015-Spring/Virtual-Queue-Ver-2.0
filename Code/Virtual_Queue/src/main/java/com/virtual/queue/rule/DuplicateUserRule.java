package com.virtual.queue.rule;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.virtual.queue.beans.RideInfo;
import com.virtual.queue.beans.RuleCapacityBean;
import com.virtual.queue.beans.User;
import com.virtual.queue.dao.RideDao;
import com.virtual.queue.dao.RideDaoImp;
import com.virtual.queue.dao.RuleDao;
import com.virtual.queue.dao.UserDao;
import com.virtual.queue.dao.UserDaoImp;
import com.virtual.queue.exception.NotificationException;
//@Component
public class DuplicateUserRule implements Rule,RuleCmd {

	public static final int MAX_RIDE_PER_USER = 3;

	//@Autowired
	//RuleDao ruleDao;

	private User user = null;
	private int rideCount = 0;

	//@Autowired
	//UserDao userDao;

	//@Autowired
	//RideDao rideDao;

	@Override
	public boolean apply() {
		if (user == null) {
			return false;
		}
		if (rideCount > MAX_RIDE_PER_USER) {
			return false;
		}

		return true;
	}

	@Override
	public void loadData(long userId, long rideId) {
		//TODO:try to use injection instead.
		UserDao userDao=new UserDaoImp();
		RideDao rideDao=new RideDaoImp();
		
		
		user = userDao.getUserById(userId); 
		 
		
		try {
			
			rideCount = rideDao.getRideByUser(userId).size();
 

		} catch (NotificationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Override
	public void loadData() {
		// TODO Auto-generated method stub

	}

	@Override
	public RuleCapacityBean loadDataRule(long userId, long rideId)
			throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

}
