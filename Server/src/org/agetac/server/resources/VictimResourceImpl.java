package org.agetac.server.resources;

import org.agetac.common.dto.Victim;
import org.agetac.common.resources.VictimResource;
import org.agetac.server.db.InterventionDAO;
import org.restlet.resource.ServerResource;


public class VictimResourceImpl extends ServerResource implements
		VictimResource {

	@Override
	public void add(Victim involved) {
		InterventionDAO dao = new InterventionDAO();

		long interId = Long.parseLong((String) getRequestAttributes().get(
				"interId"));
		dao.add(interId, involved);

	}

}
