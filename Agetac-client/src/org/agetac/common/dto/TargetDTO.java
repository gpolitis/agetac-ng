package org.agetac.common.dto;


public class TargetDTO implements IModel {

	private long id;
	
	public enum TargetType {WATER, FIRE, CHEM, HUMAN}
	
	private TargetType type;
	private PositionDTO position;
	private String name = "";
	
	public TargetDTO() {}
	
	public TargetDTO(TargetType type) {
		this.position = new PositionDTO();
	}
	
	public long getId() {
		return id;
	}
	
	public TargetDTO(String name, TargetType type, PositionDTO p) {
		this.name = name;
		this.type = type;
		this.position = p;
	}

	public TargetDTO(PositionDTO p, TargetType t) {
		this.position = p;
		this.type = t;
	}

	public TargetType getType() {
		return type;
	}

	public void setType(TargetType type) {
		this.type = type;
	}

	public PositionDTO getPosition() {
		return position;
	}

	public void setPosition(PositionDTO position) {
		this.position = position;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
