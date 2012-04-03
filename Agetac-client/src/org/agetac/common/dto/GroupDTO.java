package org.agetac.common.dto;

public class GroupDTO implements IModel {

	private long id;
	private PositionDTO position;
	private String name = "";
	

	public GroupDTO() {
		this.position = new PositionDTO();
	}
	
	public GroupDTO(String name, PositionDTO position) {
		this.name = name;
		this.position = position;
	}
	
	@Override
	public long getId() {
		return id;
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
