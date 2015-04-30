package com.virtual.queue.beans;

import java.util.Date;

public class Queue {
	private long activity_id;
	private long visitor_id;
	private String reservation_time;
	private String estimated_time;
	private int waitingTime;
	//currently other fields from Queue database is not included
	
	public long getActivityId() {
		return activity_id;
	}

	public void setActivityId(long activity_id) {
		this.activity_id = activity_id;
	}

	public long getVisitorId() {
		return visitor_id;
	}

	public void setVisitorId(long visitor_id) {
		this.visitor_id = visitor_id;
	}

	public String getReservationTime() {
		return reservation_time;
	}

	public void setReservationTime(String reservation_time) {
		this.reservation_time = reservation_time;
	}

	public String getEstimatedTime() {
		return estimated_time;
	}

	public void setEstimatedTime(String estimated_time) {
		this.estimated_time = estimated_time;
	}


}
