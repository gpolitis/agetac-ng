package org.agetac.common.dto;

import java.util.Date;

import org.agetac.common.dto.VehicleDTO.VehiculeType;

public class VehicleDemandDTO {

	public enum DemandState {ASKED, REFUSED, ACCEPTED}
	
	private Date timestamp;
	private DemandState state;
	private PositionDTO position;
	private VehiculeType category;
	
	/** default vehicule id associated with the demande is unknown (-1)*/
	private int vehiculeId = -1;
	
	public VehicleDemandDTO() {}
	
	public VehicleDemandDTO(DemandState state, VehiculeType cat, PositionDTO p, Date date) {
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

	public VehiculeType getCategory() {
		return category;
	}

	public void setCategory(VehiculeType category) {
		this.category = category;
	}

	public int getVehiculeId() {
		return vehiculeId;
	}

	public void setVehiculeId(int vehiculeId) {
		this.vehiculeId = vehiculeId;
	}
}
