package org.agetac.common.dto;

public class MessageDTO {
	
	private long id;
	private String text;
	private String date;
	
	public MessageDTO() {}
	
	public MessageDTO(String message, String date) {
		this.text = message;
		this.date = date;
	}
	
	public long getId() {
		return id;
	}
	
	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}
}
