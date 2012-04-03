package org.agetac.common.dto;


public class ActionDTO implements IModel {

	public enum ActionType {WATER, FIRE, HUMAN}
	
	private long id;
	private String name = "";
	private ActionType type;
	private PositionDTO position;
	private PositionDTO origin;
	private PositionDTO aim;
	
	public ActionDTO() {
		this.type = ActionType.FIRE;
		this.position = new PositionDTO();
		this.aim = new PositionDTO();
	}
	
	public ActionDTO(String n, ActionType t, PositionDTO p, PositionDTO a) {
		this.name = n;
		this.type = t;
		this.position = p;
		this.aim = a;
	}
	
	public ActionDTO(PositionDTO p, ActionType t, PositionDTO origin, PositionDTO aim) {
		this.position = p;
		this.type = t;
		this.origin = origin;
		this.aim = aim;
	}

	public long getId() {
		return id;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public ActionType getType() {
		return type;
	}

	public void setType(ActionType type) {
		this.type = type;
	}

	public PositionDTO getPosition() {
		return position;
	}

	public void setPosition(PositionDTO position) {
		this.position = position;
	}

	public PositionDTO getAim() {
		return aim;
	}

	public void setAim(PositionDTO aim) {
		this.aim = aim;
	}

	public PositionDTO getOrigin() {
		return origin;
	}

	public void setOrigin(PositionDTO origin) {
		this.origin = origin;
	}
	
}
