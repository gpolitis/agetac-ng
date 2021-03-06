package org.agetac.common.dto;

public class SourceDTO {

	public enum SourceType {WATER, FIRE, CHEM}
	
	private SourceType type;
	private PositionDTO position;
	private String name = "";
	
	private long id;
	
	public SourceDTO() {}
	
	public SourceDTO(SourceType type) {
		this.position = new PositionDTO();
	}
	
	public SourceDTO(String name, SourceType type, PositionDTO p) {
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

	public long getId() {
		return id;
	}
	
}
