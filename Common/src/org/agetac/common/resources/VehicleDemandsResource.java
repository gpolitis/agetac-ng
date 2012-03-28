package org.agetac.common.resources;

import java.util.Collection;

import org.agetac.common.dto.VehicleDemand;
import org.restlet.resource.Get;


public interface VehicleDemandsResource {

	@Get
	public abstract Collection<VehicleDemand> retrieve();

}
