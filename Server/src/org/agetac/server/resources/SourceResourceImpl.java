package org.agetac.server.resources;

import org.agetac.common.dto.Source;
import org.agetac.common.resources.SourceResource;
import org.agetac.server.db.InterventionDAO;
import org.restlet.resource.ServerResource;


public class SourceResourceImpl extends ServerResource implements
		SourceResource {

	@Override
	public void add(Source source) {
		InterventionDAO dao = new InterventionDAO();

		long interId = Long.parseLong((String) getRequestAttributes().get(
				"interId"));
		dao.add(interId, source);
	}

}
