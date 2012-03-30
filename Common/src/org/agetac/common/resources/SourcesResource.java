package org.agetac.common.resources;

import java.util.Collection;

import org.agetac.common.dto.SourceDTO;
import org.restlet.resource.Get;


public interface SourcesResource {

	@Get
	public abstract Collection<SourceDTO> retrieve();

}
