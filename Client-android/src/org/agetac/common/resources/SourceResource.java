package org.agetac.common.resources;

import org.agetac.common.dto.SourceDTO;
import org.restlet.resource.Delete;
import org.restlet.resource.Post;
import org.restlet.resource.Put;


public interface SourceResource {

	@Post
	public abstract SourceDTO add(SourceDTO source);

	@Put
	public abstract void update(SourceDTO source);

	@Delete
	public abstract void remove();

}
