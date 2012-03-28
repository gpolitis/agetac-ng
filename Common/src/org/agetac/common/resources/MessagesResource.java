package org.agetac.common.resources;

import java.util.Collection;

import org.agetac.common.dto.Message;
import org.restlet.resource.Get;


public interface MessagesResource {

	@Get
	public abstract Collection<Message> retrieve();

}
