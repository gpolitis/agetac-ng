package org.agetac.common.dto;

import java.util.Collection;

import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.PrimaryKey;

@PersistenceCapable
public class Intervention {

	@PrimaryKey
	private long id;

	private Collection<VehicleDemand> vehicleDemands;
	
	private Collection<Vehicle> vehicles;

	private Collection<Message> messages;
	
	private Collection<Target> targets;
	
	private Collection<Source> sources;
	
	private Collection<Victim> victims;

	
	public Collection<VehicleDemand> getVehicleDemands() {
		return vehicleDemands;
	}
	
	public Collection<Message> getMessages() {
		return messages;
	}
	
	public Collection<Target> getTargets() {
		return targets;
	}
	
	public Collection<Source> getSources() {
		return sources;
	}

	public long getId() {
		return id;
	}

	public Collection<Victim> getVictims() {
		return victims;
	}
}
