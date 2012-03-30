package org.agetac.common.dto;

import java.util.Date;

import javax.jdo.annotations.PersistenceCapable;

import org.agetac.common.dto.Vehicle.VehiculeType;

@PersistenceCapable
public class VehicleDemand {

	public enum DemandState {ASKED, REFUSED, ACCEPTED}
	
	private Date timestamp;
	private DemandState state;
	private String name;
	private Position position;
	private VehiculeType category;
	
	/** default vehicule id associated with the demande is unknown (-1)*/
	private int vehiculeId = -1;
	
	public VehicleDemand() {}
	
	public VehicleDemand(String name, DemandState state, VehiculeType cat, Position p, Date date) {
		this.name = name;
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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Position getPosition() {
		return position;
	}

	public void setPosition(Position position) {
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
