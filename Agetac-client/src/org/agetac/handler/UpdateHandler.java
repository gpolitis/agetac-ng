package org.agetac.handler;

import org.agetac.common.client.AgetacClient;
import org.agetac.entity.EntityList;
import org.agetac.entity.IEntity;

public class UpdateHandler implements IHandler {

	private EntityList entities;
	private AgetacClient client;
	
	public UpdateHandler(EntityList entList, AgetacClient client) {
		this.entities = entList;
		this.client = client;
	}

	@Override
	public void handle(IEntity entity) {
		System.out.println("EditHandler: not implemented yet");
	}
}
