package org.agetac.common.resources;

import java.util.Collection;

import org.agetac.common.dto.TargetDTO;
import org.restlet.resource.Get;


public interface TargetsResource {

	@Get
	public abstract Collection<TargetDTO> retrieve();

}
