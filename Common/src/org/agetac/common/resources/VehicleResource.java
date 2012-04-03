package org.agetac.common.resources;

import org.agetac.common.dto.VehicleDTO;
import org.restlet.resource.Delete;
import org.restlet.resource.Post;
import org.restlet.resource.Put;


public interface VehicleResource {

	@Post
	public abstract VehicleDTO add(VehicleDTO vehicle);

	@Put
	public abstract void update(VehicleDTO vehicle);

	@Delete
	public abstract void remove();

}
