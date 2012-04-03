package org.agetac.common.resources;

import org.agetac.common.dto.ActionDTO;
import org.restlet.resource.Delete;
import org.restlet.resource.Post;
import org.restlet.resource.Put;


public interface ActionResource {

	@Post
	public abstract ActionDTO add(ActionDTO action);

	@Put
	public abstract void update(ActionDTO action);

	@Delete
	public abstract void remove();

}
