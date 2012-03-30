package org.agetac.common.resources;

import org.agetac.common.dto.VehicleDemandDTO;
import org.restlet.resource.Post;


public interface VehicleDemandResource {

	@Post
	public abstract VehicleDemandDTO add(VehicleDemandDTO vehicleDemand);

}
