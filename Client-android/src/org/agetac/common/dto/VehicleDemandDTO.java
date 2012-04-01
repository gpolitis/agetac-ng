package org.agetac.common.dto;

import java.util.Date;

import org.agetac.common.dto.VehicleDTO.VehicleType;

public class VehicleDemandDTO {

	public enum DemandState {ASKED, REFUSED, ACCEPTED}
	
	private Date timestamp;
	private DemandState state;
	private PositionDTO position;
	private VehicleType category;
	
	/** default vehicle id associated with the demand is unknown (-1)*/
	private int vehicleId = -1;
	
	public VehicleDemandDTO() {}
	
	public VehicleDemandDTO(DemandState state, VehicleType cat, PositionDTO p, Date date) {
		this.state = state;
		this.category = cat;
		this.position = p;
		this.timestamp = date;
	}

	public Date getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}

	public DemandState getState() {
		return state;
	}

	public void setState(DemandState state) {
		this.state = state;
	}

	public PositionDTO getPosition() {
		return position;
	}

	public void setPosition(PositionDTO position) {
		this.position = position;
	}

	public VehicleType getCategory() {
		return category;
	}

	public void setCategory(VehicleType category) {
		this.category = category;
	}

	public int getVehicleId() {
		return vehicleId;
	}

	public void setVehicleId(int vehicleId) {
		this.vehicleId = vehicleId;
	}
}
