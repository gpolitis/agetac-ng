package org.agetac.server.resources;

import org.agetac.common.dto.Victim;
import org.agetac.common.resources.VictimResource;
import org.agetac.server.db.InterventionDAO;
import org.agetac.server.db.SimpleDAO;
import org.restlet.resource.ServerResource;

public class VictimResourceImpl extends ServerResource implements
		VictimResource {

	@Override
	public Victim add(Victim victim) {
		InterventionDAO dao = new InterventionDAO();

		long interId = Long.parseLong((String) getRequestAttributes().get(
				"interId"));
		dao.add(interId, victim);

		return victim;
	}

	@Override
	public void update(Victim victim) {
		// TODO Auto-generated method stub

	}

	@Override
	public void remove() {
		long victimId = Long.parseLong((String) getRequestAttributes().get(
				"victimId"));

		SimpleDAO.getInstance().delete(Victim.class, victimId);

	}

}
