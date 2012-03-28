package org.agetac.common.resources;

import java.util.Collection;

import org.agetac.common.dto.Victim;
import org.restlet.resource.Get;


public interface VictimsResource {

	@Get
	public abstract Collection<Victim> retrieve();

}
