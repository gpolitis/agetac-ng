package org.agetac.common.resources;

import org.agetac.common.dto.TargetDTO;
import org.restlet.resource.Delete;
import org.restlet.resource.Post;
import org.restlet.resource.Put;


public interface TargetResource {

	@Post
	public abstract TargetDTO add(TargetDTO target);

	@Put
	public abstract void update(TargetDTO target);

	@Delete
	public abstract void remove();

}
