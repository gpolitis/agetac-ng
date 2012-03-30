package org.agetac.common.resources;

import org.agetac.common.dto.Target;
import org.restlet.resource.Delete;
import org.restlet.resource.Post;
import org.restlet.resource.Put;


public interface TargetResource {

	@Post
	public abstract Target add(Target target);

	@Put
	public abstract void update(Target target);

	@Delete
	public abstract void remove();

}
