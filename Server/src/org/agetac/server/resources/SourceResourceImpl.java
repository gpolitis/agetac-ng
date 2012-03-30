package org.agetac.server.resources;

import org.agetac.common.dto.Source;
import org.agetac.common.resources.SourceResource;
import org.agetac.server.db.InterventionDAO;
import org.agetac.server.db.SimpleDAO;
import org.restlet.resource.ServerResource;

public class SourceResourceImpl extends ServerResource implements
		SourceResource {

	@Override
	public Source add(Source source) {
		InterventionDAO dao = new InterventionDAO();

		long interId = Long.parseLong((String) getRequestAttributes().get(
				"interId"));
		dao.add(interId, source);
		
		return source;
	}

	@Override
	public void update(Source source) {
		// TODO Auto-generated method stub

	}

	@Override
	public void remove() {
		long sourceId = Long.parseLong((String) getRequestAttributes().get(
				"sourceId"));

		SimpleDAO.getInstance().delete(Source.class, sourceId);

	}

}
