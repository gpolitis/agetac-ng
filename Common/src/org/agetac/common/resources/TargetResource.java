package org.agetac.common.resources;

import org.agetac.common.dto.Target;
import org.restlet.resource.Post;


public interface TargetResource {

	@Post
	public abstract void add(Target target);

}
