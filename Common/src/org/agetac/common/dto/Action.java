package org.agetac.common.dto;

import javax.jdo.annotations.PersistenceCapable;

@PersistenceCapable
public class Action {

	public enum ActionType {WATER, FIRE, HUMAN}
	
	private String name = "";
	private ActionType type;
	private Position position;
	private Position aim;
	
	public Action() {
		this.type = ActionType.FIRE;
		this.position = new Position();
		this.aim = new Position();
	}
	
	public Action(String n, ActionType t, Position p, Position a) {
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

	public Position getPosition() {
		return position;
	}

	public void setPosition(Position position) {
		this.position = position;
	}

	public Position getAim() {
		return aim;
	}

	public void setAim(Position aim) {
		this.aim = aim;
	}
	
}
