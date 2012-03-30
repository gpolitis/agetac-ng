package org.agetac.common.dto;

import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

@PersistenceCapable(identityType = IdentityType.APPLICATION)
public class Target {

	@PrimaryKey
	@Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
	private long id;
	
	public enum TargetType {WATER, FIRE, CHEM, HUMAN}
	
	private TargetType type;
	private Position position;
	private String name = "";
	
	public Target() {}
	
	public Target(TargetType type) {
		this.position = new Position();
	}
	
	public long getId() {
		return id;
	}
	
	public Target(String name, TargetType type, Position p) {
		this.name = name;
		this.type = type;
		this.position = p;
	}

	public TargetType getType() {
		return type;
	}

	public void setType(TargetType type) {
		this.type = type;
	}

	public Position getPosition() {
		return position;
	}

	public void setPosition(Position position) {
		this.position = position;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
