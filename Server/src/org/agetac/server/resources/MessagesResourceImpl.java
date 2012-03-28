package org.agetac.server.resources;

import java.util.Collection;

import org.agetac.common.dto.Message;
import org.agetac.common.resources.MessagesResource;
import org.agetac.server.db.InterventionDAO;
import org.restlet.resource.ServerResource;


public class MessagesResourceImpl extends ServerResource implements
		MessagesResource {

	@Override
	public Collection<Message> retrieve() {
		InterventionDAO dao = new InterventionDAO();

		long interId = Long.parseLong((String) getRequestAttributes().get(
				"interId"));
		return dao.retrieveMessages(interId);
	}

}
