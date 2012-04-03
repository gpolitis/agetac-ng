package org.agetac.common.resources;

import java.util.Collection;

import org.agetac.common.dto.VehicleDTO;
import org.restlet.resource.Get;



public interface VehiclesResource {

	@Get
	public abstract Collection<VehicleDTO> retrieve();

}
