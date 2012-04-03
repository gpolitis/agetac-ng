package org.agetac.common.dto;

public interface IModel {

	public long getId();
	
	public PositionDTO getPosition();

	public void setPosition(PositionDTO p);
	
	public String getName();
	
	public void setName(String name);
}
