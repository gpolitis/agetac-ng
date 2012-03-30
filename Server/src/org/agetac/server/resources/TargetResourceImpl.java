package org.agetac.server.resources;

import org.agetac.common.dto.Target;
import org.agetac.common.resources.TargetResource;
import org.agetac.server.db.InterventionDAO;
import org.agetac.server.db.SimpleDAO;
import org.restlet.resource.ServerResource;

public class TargetResourceImpl extends ServerResource implements
		TargetResource {

	@Override
	public Target add(Target target) {
		InterventionDAO dao = new InterventionDAO();

		long interId = Long.parseLong((String) getRequestAttributes().get(
				"interId"));
		dao.add(interId, target);
		
		return target;
	}

	@Override
	public void update(Target target) {
		// TODO Auto-generated method stub

	}

	@Override
	public void remove() {
		long targetId = Long.parseLong((String) getRequestAttributes().get(
				"targetId"));

		SimpleDAO.getInstance().delete(Target.class, targetId);

	}

}
