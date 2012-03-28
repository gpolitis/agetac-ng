package org.agetac.common.resources;

import org.agetac.common.dto.VehicleDemand;
import org.restlet.resource.Post;


public interface VehicleDemandResource {

	@Post
	public abstract void add(VehicleDemand vehicleDemand);

}
