package org.agetac.common.dto;

import javax.jdo.annotations.PersistenceCapable;

@PersistenceCapable
public class Victim {

	private String name = "";
	
	public Victim() {}
	
	public Victim(String name) {
		this.name = name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getName() {
		return name;
	}
}
