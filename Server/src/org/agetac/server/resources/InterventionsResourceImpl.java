package org.agetac.server.resources;

import java.util.Collection;

import org.agetac.common.dto.Intervention;
import org.agetac.common.resources.InterventionsResource;
import org.agetac.server.db.SimpleDAO;
import org.restlet.resource.ServerResource;


public class InterventionsResourceImpl extends ServerResource implements
		InterventionsResource {

	@Override
	public Collection<Intervention> retrieve() {
		return SimpleDAO.getInstance().getMany(Intervention.class);
	}

}
