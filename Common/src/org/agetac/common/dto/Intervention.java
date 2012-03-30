package org.agetac.common.dto;

import java.util.Collection;

import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

@PersistenceCapable(identityType = IdentityType.APPLICATION)
public class Intervention {

	@PrimaryKey
	@Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
	private long id;

	private Collection<VehicleDemand> vehicleDemands;

	private Collection<Vehicle> vehicles;

	private Collection<Message> messages;

	private Collection<Target> targets;

	private Collection<Source> sources;

	@Persistent(mappedBy = "intervention")
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

	public Collection<Vehicle> getVehicles() {
		return vehicles;
	}

	public long getId() {
		return id;
	}

	public Collection<Victim> getVictims() {
		return victims;
	}
}
