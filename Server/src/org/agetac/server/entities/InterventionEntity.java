package org.agetac.server.entities;

import java.util.Collection;

import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

@PersistenceCapable(identityType = IdentityType.APPLICATION)
public class InterventionEntity {

	@PrimaryKey
	@Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
	private long id;

	@Persistent(mappedBy = "intervention")
	private Collection<VehicleDemandEntity> vehicleDemands;

	private Collection<VehicleEntity> vehicles;

	@Persistent(mappedBy = "intervention")
	private Collection<MessageEntity> messages;

	@Persistent(mappedBy = "intervention")
	private Collection<TargetEntity> targets;

	@Persistent(mappedBy = "intervention")
	private Collection<SourceEntity> sources;

	@Persistent(mappedBy = "intervention")
	private Collection<VictimEntity> victims;

	public Collection<VehicleDemandEntity> getVehicleDemands() {
		return vehicleDemands;
	}

	public Collection<MessageEntity> getMessages() {
		return messages;
	}

	public Collection<TargetEntity> getTargets() {
		return targets;
	}

	public Collection<SourceEntity> getSources() {
		return sources;
	}

	public Collection<VehicleEntity> getVehicles() {
		return vehicles;
	}

	public long getId() {
		return id;
	}

	public Collection<VictimEntity> getVictims() {
		return victims;
	}
}
