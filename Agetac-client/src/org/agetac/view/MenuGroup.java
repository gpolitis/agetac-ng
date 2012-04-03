package org.agetac.view;

import java.util.ArrayList;

import org.agetac.entity.IEntity;


public class MenuGroup {
	
	private String groupName;
	private ArrayList<IEntity> entities;

	public MenuGroup(String name) {
		this.groupName = name;
		this.entities = new ArrayList<IEntity>();
	}

	public String getGroupName() {
		return groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

	public ArrayList<IEntity> getEntities() {
		return entities;
	}

	public void setEntities(ArrayList<IEntity> entities) {
		this.entities = entities;
	}

}
