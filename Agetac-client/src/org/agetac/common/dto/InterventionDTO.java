package org.agetac.common.dto;

import java.util.ArrayList;
import java.util.Collection;

public class InterventionDTO implements IModel {

	private long id;
	private Collection<VehicleDTO> vehicles = new ArrayList<VehicleDTO>();
	private Collection<SourceDTO> sources = new ArrayList<SourceDTO>();
	private Collection<MessageDTO> messages = new ArrayList<MessageDTO>();
	private Collection<TargetDTO> targets = new ArrayList<TargetDTO>();
	private Collection<ActionDTO> actions = new ArrayList<ActionDTO>();
	private Collection<VictimDTO> victims = new ArrayList<VictimDTO>();
	private Collection<VehicleDemandDTO> demands = new ArrayList<VehicleDemandDTO>();
	private PositionDTO position;
	private String name;
	
	public InterventionDTO() {
		this.position = new PositionDTO();
	}
	
	public long getId() {
		return id;
	}
	
	public Collection<VehicleDTO> getVehicles() {
		return vehicles;
	}

	public Collection<SourceDTO> getSources() {
		return sources;
	}

	public Collection<MessageDTO> getMessages() {
		return messages;
	}

	public Collection<TargetDTO> getTargets() {
		return targets;
	}

	public Collection<VictimDTO> getVictims() {
		return victims;
	}
	
	public Collection<VehicleDemandDTO> getDemands() {
		return demands;
	}
	
	public Collection<ActionDTO> getActions() {
		return actions;
	}

	@Override
	public PositionDTO getPosition() {
		return position;
	}

	@Override
	public void setPosition(PositionDTO p) {
		this.position = p;
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public void setName(String name) {
		this.name = name;
	}
}
