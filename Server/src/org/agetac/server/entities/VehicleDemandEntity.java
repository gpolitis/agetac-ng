package org.agetac.server.entities;

import java.util.Date;

import javax.jdo.annotations.PersistenceCapable;

import org.agetac.server.entities.VehicleEntity.VehiculeType;

@PersistenceCapable
public class VehicleDemandEntity {

	public enum DemandState {ASKED, REFUSED, ACCEPTED}
	
	private Date timestamp;
	private DemandState state;
	private PositionEntity position;
	private VehiculeType category;
	
	private InterventionEntity intervention;
	
	/** default vehicule id associated with the demande is unknown (-1)*/
	private int vehiculeId = -1;
	
	public VehicleDemandEntity() {}
	
	public VehicleDemandEntity(DemandState state, VehiculeType cat, PositionEntity p, Date date) {
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

	public PositionEntity getPosition() {
		return position;
	}

	public void setPosition(PositionEntity position) {
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
