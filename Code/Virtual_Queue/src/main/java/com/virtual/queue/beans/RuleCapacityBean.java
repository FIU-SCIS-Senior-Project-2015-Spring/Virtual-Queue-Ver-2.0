package com.virtual.queue.beans;

import java.util.LinkedList;
import java.util.List;

public class RuleCapacityBean {
	private RideInfo ride = null;
	private QueueInfo queueInfo = null;
	private LinkedList<RideInfo> rideList = null;
	private List<User> userList = null;

	
	
	public RuleCapacityBean(final RideInfo rideInf,final QueueInfo queueInf,final LinkedList<RideInfo> rideLst,final List<User> userLst){
		
		ride=rideInf;
		queueInfo=queueInf;
		rideList=rideLst;
		userList=userLst;
		
		
	}
	public RideInfo getRide() {
		return ride;
	}

	public void setRide(RideInfo ride) {
		this.ride = ride;
	}

	public QueueInfo getQueueInfo() {
		return queueInfo;
	}

	public void setQueueInfo(QueueInfo queueInfo) {
		this.queueInfo = queueInfo;
	}

	public LinkedList<RideInfo> getRideList() {
		return rideList;
	}

	public void setRideList(LinkedList<RideInfo> rideList) {
		this.rideList = rideList;
	}

	public List<User> getUserList() {
		return userList;
	}

	public void setUserList(List<User> userList) {
		this.userList = userList;
	}

}
