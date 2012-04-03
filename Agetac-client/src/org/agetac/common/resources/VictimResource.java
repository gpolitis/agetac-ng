package org.agetac.common.resources;

import org.agetac.common.dto.VictimDTO;
import org.restlet.resource.Delete;
import org.restlet.resource.Post;
import org.restlet.resource.Put;


public interface VictimResource {

	@Post
	public abstract VictimDTO add(VictimDTO victim);

	@Put
	public abstract void update(VictimDTO victim);

	@Delete
	public abstract void remove();

}
