package org.agetac.common.dto;

public class VictimDTO {

	private long id;

	public long getId() {
		return id;
	}

	private String name = "";

	public VictimDTO() {
	}

	public VictimDTO(String name) {
		this.name = name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}
}
