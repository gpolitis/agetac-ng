package org.agetac.server.entities;

import javax.jdo.annotations.PersistenceCapable;

@PersistenceCapable
public class ActionEntity {

	public enum ActionType {WATER, FIRE, HUMAN}
	
	private String name = "";
	private ActionType type;
	private PositionEntity position;
	private PositionEntity aim;
	
	public ActionEntity() {
		this.type = ActionType.FIRE;
		this.position = new PositionEntity();
		this.aim = new PositionEntity();
	}
	
	public ActionEntity(String n, ActionType t, PositionEntity p, PositionEntity a) {
		this.name = n;
		this.type = t;
		this.position = p;
		this.aim = a;
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

	public PositionEntity getPosition() {
		return position;
	}

	public void setPosition(PositionEntity position) {
		this.position = position;
	}

	public PositionEntity getAim() {
		return aim;
	}

	public void setAim(PositionEntity aim) {
		this.aim = aim;
	}
	
}
