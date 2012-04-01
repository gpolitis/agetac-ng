package org.agetac.common.resources;

import java.util.Collection;

import org.agetac.common.dto.InterventionDTO;
import org.restlet.resource.Get;


public interface InterventionsResource {

	@Get
	public abstract Collection<InterventionDTO> retrieve();

}
