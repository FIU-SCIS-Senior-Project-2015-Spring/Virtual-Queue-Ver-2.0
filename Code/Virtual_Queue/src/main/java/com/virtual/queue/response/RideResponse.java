package com.virtual.queue.response;

public class RideResponse {
	private String ride_id;
	private String ride_name;

	public RideResponse(String rId, String rName) {

		ride_id = rId;

	}

	public String getUse_id() {
		return ride_id;
	}

	public void setUse_id(String use_id) {
		this.ride_id = use_id;
	}

	public String getRide_name() {
		return ride_name;
	}

	public void setRide_name(String ride_name) {
		this.ride_name = ride_name;
	}

}
