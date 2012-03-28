package org.agetac.server.resources;

import java.util.Collection;

import org.agetac.common.dto.Source;
import org.agetac.common.resources.SourcesResource;
import org.agetac.server.db.InterventionDAO;
import org.restlet.resource.ServerResource;


public class SourcesResourceImpl extends ServerResource implements
		SourcesResource {

	@Override
	public Collection<Source> retrieve() {
		InterventionDAO dao = new InterventionDAO();

		long interId = Long.parseLong((String) getRequestAttributes().get(
				"interId"));
		return dao.retrieveSources(interId);
	}

}
