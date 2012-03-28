package org.agetac.common.resources;

import org.agetac.common.dto.Intervention;
import org.restlet.resource.Post;


public interface InterventionResource {

	@Post
	void add(Intervention intervention);

}
