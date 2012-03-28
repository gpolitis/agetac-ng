package org.agetac.common.resources;

import java.util.Collection;

import org.agetac.common.dto.Target;
import org.restlet.resource.Get;


public interface TargetsResource {

	@Get
	public abstract Collection<Target> retrieve();

}
