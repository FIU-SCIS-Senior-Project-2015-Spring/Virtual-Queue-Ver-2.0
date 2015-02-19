package com.virtual.queue.request;

import java.util.Date;

import com.virtual.queue.beans.User;

public class UserRideRequest {
	private User user;
 public Date getRidetime() {
	return Ridetime;
}
public void setRidetime(Date ridetime) {
	Ridetime = ridetime;
}
public Date getNotificationTime() {
	return notificationTime;
}
public void setNotificationTime(Date notificationTime) {
	this.notificationTime = notificationTime;
}
private Date Ridetime;
 private Date notificationTime;
public User getUser() {
	return user;
}
public void setUser(User user) {
	this.user = user;
}
 
}
