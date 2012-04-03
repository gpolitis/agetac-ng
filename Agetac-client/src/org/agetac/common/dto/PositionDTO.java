package org.agetac.common.dto;

public class PositionDTO {

	private long id;
	private double latitude = 0;
	private double longitude = 0;
	
	public PositionDTO() {}
	
	public PositionDTO(double latitude, double longitude) {
		this.latitude = latitude;
		this.longitude = longitude;
	}
	
	public PositionDTO(PositionDTO p) {
		this.latitude = p.getLatitude();
		this.longitude = p.getLongitude();
	}

	public long getId() {
		return id;
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
