package org.agetac.server.resources;

import org.agetac.common.dto.Message;
import org.agetac.common.resources.MessageResource;
import org.agetac.server.db.InterventionDAO;
import org.restlet.resource.ServerResource;


public class MessageResourceImpl extends ServerResource implements
		MessageResource {

	@Override
	public Message add(Message message) {
		InterventionDAO dao = new InterventionDAO();

		long interId = Long.parseLong((String) getRequestAttributes().get(
				"interId"));
		dao.add(interId, message);
		
		return message;
	}

}
