package org.agetac.common.dto;

import java.util.ArrayList;
import java.util.Collection;

public class InterventionDTO {

	private long id;

	public long getId() {
		return id;
	}

	private Collection<VehicleDTO> vehicles = new ArrayList<VehicleDTO>();
	
	private Collection<SourceDTO> sources = new ArrayList<SourceDTO>();
	
	private Collection<MessageDTO> messages = new ArrayList<MessageDTO>();
	
	private Collection<TargetDTO> targets = new ArrayList<TargetDTO>();
	
	private Collection<VictimDTO> victims = new ArrayList<VictimDTO>();

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
}
