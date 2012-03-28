package org.agetac.server.resources;

import java.util.Collection;

import org.agetac.common.dto.VehicleDemand;
import org.agetac.common.resources.VehicleDemandsResource;
import org.agetac.server.db.InterventionDAO;
import org.restlet.resource.ServerResource;


public class VehicleDemandsResourceImpl extends ServerResource implements
		VehicleDemandsResource {

	@Override
	public Collection<VehicleDemand> retrieve() {
		InterventionDAO dao = new InterventionDAO();

		long interId = Long.parseLong((String) getRequestAttributes().get(
				"interId"));
		return dao.retrieveVehicleDemands(interId);

	}

}
