package org.agetac.common.resources;

import java.util.Collection;

import org.agetac.common.dto.Source;
import org.restlet.resource.Get;


public interface SourcesResource {

	@Get
	public abstract Collection<Source> retrieve();

}
