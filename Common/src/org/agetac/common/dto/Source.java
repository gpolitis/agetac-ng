package org.agetac.common.dto;

import javax.jdo.annotations.PersistenceCapable;

@PersistenceCapable
public class Source {

	public enum SourceType {WATER, FIRE, CHEM}
	
	private SourceType type;
	private Position position;
	private String name = "";
	
	public Source() {}
	
	public Source(SourceType type) {
		this.position = new Position();
	}
	
	public Source(String name, SourceType type, Position p) {
		this.name = name;
		this.type = type;
		this.position = p;
	}

	public SourceType getType() {
		return type;
	}

	public void setType(SourceType type) {
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
