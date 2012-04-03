package org.agetac.common.resources;

import java.util.Collection;

import org.agetac.common.dto.ActionDTO;
import org.restlet.resource.Get;


public interface ActionsResource {

	@Get
	public abstract Collection<ActionDTO> retrieve();

}
