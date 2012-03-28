package org.agetac.common.resources;

import org.agetac.common.dto.Source;
import org.restlet.resource.Post;


public interface SourceResource {

	@Post
	public abstract void add(Source source);

}
