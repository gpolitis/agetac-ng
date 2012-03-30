package org.agetac.server.resources;

import java.util.Collection;

import org.agetac.common.dto.VehicleDemandDTO;
import org.agetac.common.resources.VehicleDemandsResource;
import org.agetac.server.db.InterventionDAO;
import org.restlet.resource.ServerResource;


public class VehicleDemandsResourceImpl extends ServerResource implements
		VehicleDemandsResource {

	@Override
	public Collection<VehicleDemandDTO> retrieve() {
		InterventionDAO dao = new InterventionDAO();

		long interId = Long.parseLong((String) getRequestAttributes().get(
				"interId"));
		return dao.retrieveVehicleDemands(interId);

	}

}
