package org.agetac.common.resources;

import org.agetac.common.dto.InterventionDTO;
import org.restlet.resource.Post;


public interface InterventionResource {

	@Post
	void add(InterventionDTO intervention);

}
