package org.agetac.common.dto;

import java.util.Date;

import org.agetac.common.dto.VehicleDTO.VehicleType;
import org.agetac.common.dto.VehicleDemandDTO.DemandState;

public class VehicleDemandDTO implements IModel {

	public enum DemandState {ASKED, REFUSED, ACCEPTED}
	
	private long id;
	private Date timestamp;
	private DemandState state;
	private PositionDTO position;
	private VehicleType type;
	private String name;
	private GroupDTO group;
	
	/** default vehicle id associated with the demand is unknown (-1)*/
	private int vehicleId = -1;
	
	public VehicleDemandDTO() {
		this.position = new PositionDTO();
	}
	
	public VehicleDemandDTO(DemandState s, VehicleType t, PositionDTO p, Date date) {
		this.state = s;
		this.type = t;
		this.position = new PositionDTO();
		this.timestamp = date;
	}
	
	public VehicleDemandDTO(String n, PositionDTO p, DemandState s, GroupDTO g) {
		this.name = n;
		this.position = p;
		this.state = s;
		this.group = g;
	}

	public long getId() {
		return id;
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

	public VehicleType getType() {
		return type;
	}

	public void setType(VehicleType type) {
		this.type = type;
	}

	public int getVehicleId() {
		return vehicleId;
	}

	public void setVehicleId(int vehicleId) {
		this.vehicleId = vehicleId;
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public void setName(String name) {
		this.name = name;
	}

	public GroupDTO getGroup() {
		return group;
	}

	public void setGroup(GroupDTO group) {
		this.group = group;
	}
}
