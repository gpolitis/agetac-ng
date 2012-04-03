package org.agetac.common.dto;

public class VictimDTO implements IModel {

	private long id;
	private PositionDTO position;
	private String name = "";

	public VictimDTO() {
		this.position = new PositionDTO();
	}

	public VictimDTO(String name) {
		this.name = name;
		this.position = new PositionDTO();
	}
	
	public long getId() {
		return id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	@Override
	public PositionDTO getPosition() {
		return position;
	}

	@Override
	public void setPosition(PositionDTO p) {
		this.position = p;
	}
}
