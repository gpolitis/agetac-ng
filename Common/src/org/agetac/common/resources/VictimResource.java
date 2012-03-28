package org.agetac.common.resources;

import org.agetac.common.dto.Victim;
import org.restlet.resource.Post;


public interface VictimResource {

	@Post
	public abstract void add(Victim victim);

}
