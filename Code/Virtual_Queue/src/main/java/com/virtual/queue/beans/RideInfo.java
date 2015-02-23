package com.virtual.queue.beans;

public class RideInfo {
	//this class is used for activities table
	
	private String description;
	private long startTime;
	private long endTime;
	//TODO:this may be a Double 
	private int interval;
	private int capacity;
	private long rideId;
	private String rName;
	private Coordinate coordinate;
	private double waitingTime;
	private int timePerEvent;
	private int entryTime;
	private int exitTime;
	
	public String getrName() {
		return rName;
	}

	public void setrName(String rName) {
		this.rName = rName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public long getStartTime() {
		return startTime;
	}

	public void setStartTime(long startTime) {
		this.startTime = startTime;
	}

	public long getEndTime() {
		return endTime;
	}

	public void setEndTime(long endTime) {
		this.endTime = endTime;
	}

	public int getInterval() {
		return interval;
	}

	public void setInterval(int interval) {
		this.interval = interval;
	}

	public int getCapacity() {
		return capacity;
	}

	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}

	public long getRideId() {
		return rideId;
	}

	public void setRideId(long rideId) {
		this.rideId = rideId;
	}

	public Coordinate getCoordinate() {
		return coordinate;
	}

	public void setCoordinate(Coordinate coordinate) {
		this.coordinate = coordinate;
	}

	public double getWaitingTime() {
		return waitingTime;
	}

	public void setWaitingTime(double waitingTime) {
		this.waitingTime = waitingTime;
	}
	
	public int getTimePerEvent(){
		return timePerEvent;
	}
	
	public void setTimePerEvent(int timePerEvent){
		this.timePerEvent = timePerEvent;
	}
	
	public int getEntryTime(){
		return entryTime;
				
	}
	
	public void setEntryTime(int entryTime){
		this.entryTime = entryTime;
	}
	
	public int getExitTime(){
		return exitTime;
	}
	
	public void setExitTime(int exitTime){
		this.exitTime = exitTime;
	}
	
	

}




