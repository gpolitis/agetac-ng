package org.agetac.server.resources;

import org.agetac.common.dto.Intervention;
import org.agetac.common.resources.InterventionResource;
import org.agetac.server.db.SimpleDAO;
import org.restlet.resource.ServerResource;


public class InterventionResourceImpl extends ServerResource implements
		InterventionResource {

	@Override
	public void add(Intervention intervention) {
		SimpleDAO.getInstance().add(intervention);
	}

}
