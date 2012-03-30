package org.agetac.common.resources;

import org.agetac.common.dto.Message;
import org.restlet.resource.Post;

public interface MessageResource {

	@Post
	public abstract Message add(Message message);

}
