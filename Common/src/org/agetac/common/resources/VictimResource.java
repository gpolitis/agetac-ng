package org.agetac.common.resources;

import org.agetac.common.dto.Victim;
import org.restlet.resource.Delete;
import org.restlet.resource.Post;
import org.restlet.resource.Put;


public interface VictimResource {

	@Post
	public abstract Victim add(Victim victim);

	@Put
	public abstract void update(Victim victim);

	@Delete
	public abstract void remove();

}
