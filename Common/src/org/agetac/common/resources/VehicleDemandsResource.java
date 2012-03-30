package org.agetac.common.resources;

import java.util.Collection;

import org.agetac.common.dto.VehicleDemandDTO;
import org.restlet.resource.Get;


public interface VehicleDemandsResource {

	@Get
	public abstract Collection<VehicleDemandDTO> retrieve();

}
