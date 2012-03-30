package org.agetac.common.dto;

import javax.jdo.annotations.PersistenceCapable;

@PersistenceCapable
public class Position {

	private double latitude = 0;
	private double longitude = 0;
	
	public Position() {}
	
	public Position(double latitude, double longitude) {
		this.latitude = latitude;
		this.longitude = longitude;
	}

	public double getLatitude() {
		return latitude;
	}

	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}

	public double getLongitude() {
		return longitude;
	}

	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}
	
}
