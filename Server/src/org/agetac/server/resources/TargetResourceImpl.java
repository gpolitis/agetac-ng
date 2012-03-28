package org.agetac.server.resources;

import org.agetac.common.dto.Target;
import org.agetac.common.resources.TargetResource;
import org.agetac.server.db.InterventionDAO;
import org.restlet.resource.ServerResource;


public class TargetResourceImpl extends ServerResource implements
		TargetResource {

	@Override
	public void add(Target target) {
		InterventionDAO dao = new InterventionDAO();

		long interId = Long.parseLong((String) getRequestAttributes().get(
				"interId"));
		dao.add(interId, target);
	}

}
