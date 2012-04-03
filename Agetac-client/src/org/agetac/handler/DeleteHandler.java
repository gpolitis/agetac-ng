package org.agetac.handler;


import org.agetac.common.client.AgetacClient;
import org.agetac.common.dto.ActionDTO;
import org.agetac.common.dto.SourceDTO;
import org.agetac.common.dto.TargetDTO;
import org.agetac.common.dto.VehicleDTO;
import org.agetac.common.dto.VehicleDemandDTO;
import org.agetac.common.dto.VictimDTO;
import org.agetac.entity.EntityList;
import org.agetac.entity.IEntity;

public class DeleteHandler implements IHandler {

	private EntityList entities;
	private AgetacClient client;
	
	public DeleteHandler(EntityList entList, AgetacClient client) {
		this.entities = entList;
		this.client = client;
	}

	@Override
	public void handle(IEntity entity) {
		
		if (entity.getModel() instanceof VehicleDTO) {
			VehicleDTO v = (VehicleDTO) entity.getModel();
			client.deleteVehicle(v.getId());
			entities.remove(entity);
		
		} else if (entity.getModel() instanceof ActionDTO) {
			ActionDTO a = (ActionDTO) entity.getModel();
			client.deleteAction(a.getId());
			entities.remove(entity);
		
		} else if (entity.getModel() instanceof SourceDTO) {
			SourceDTO s = (SourceDTO) entity.getModel();
			client.deleteSource(s.getId());
			entities.remove(entity);
		
		} else if (entity.getModel() instanceof TargetDTO) {
			TargetDTO c = (TargetDTO) entity.getModel();
			client.deleteTarget(c.getId());
			entities.remove(entity);
		
		} else if (entity.getModel() instanceof VictimDTO) {
			VictimDTO i = (VictimDTO) entity.getModel();
			client.deleteVictim(i.getId());
			entities.remove(entity);
		
		} else if (entity.getModel() instanceof VehicleDemandDTO) {
			VehicleDemandDTO dm = (VehicleDemandDTO) entity.getModel();
			client.deleteVehicleDemand(dm.getId());
			entities.remove(entity);
		}
	}
}
