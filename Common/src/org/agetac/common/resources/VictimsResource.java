package org.agetac.common.resources;

import java.util.Collection;

import org.agetac.common.dto.VictimDTO;
import org.restlet.resource.Get;


public interface VictimsResource {

	@Get
	public abstract Collection<VictimDTO> retrieve();

}
