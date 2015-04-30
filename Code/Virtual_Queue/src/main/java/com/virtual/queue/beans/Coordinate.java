package com.virtual.queue.beans;

import java.math.BigDecimal;

public class Coordinate {

	private BigDecimal latitude;
	private BigDecimal longitude;

	public Coordinate() {
	}

	public Coordinate(BigDecimal platitude, BigDecimal plongitude) {
		latitude = platitude;
		longitude = plongitude;

	}

	public BigDecimal getLatitude() {
		return latitude;
	}

	public void setLatitude(BigDecimal latitude) {
		this.latitude = latitude;
	}

	public BigDecimal getLongitude() {
		return longitude;
	}

	public void setLongitude(BigDecimal longitude) {
		this.longitude = longitude;
	}

	public boolean isEmpty() {

		return (longitude.compareTo(new BigDecimal(0)) ==0  && latitude.compareTo(new BigDecimal(0)) ==0);

	}

}
