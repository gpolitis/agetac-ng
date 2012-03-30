package org.agetac.server.resources;

import java.util.Collection;

import org.agetac.common.dto.Victim;
import org.agetac.common.resources.VictimsResource;
import org.agetac.server.db.InterventionDAO;
import org.restlet.resource.ServerResource;


public class VictimsResourceImpl extends ServerResource implements
		VictimsResource {

	@Override
	public Collection<Victim> retrieve() {
		InterventionDAO dao = new InterventionDAO();

		long interId = Long.parseLong((String) getRequestAttributes().get(
				"interId"));

		return dao.retrieveVictims(interId);
	}

}
