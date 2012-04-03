package org.agetac.entity;

import java.util.ArrayList;

import org.agetac.common.dto.IModel;

public class EntityList extends ArrayList<IEntity> {

	private static final long serialVersionUID = 6192402122463398955L;

	/**
	 * Return an entity that matches with the id
	 * @param uniqueID
	 * @return
	 */
	public <T extends IModel> IEntity find(long uid, Class<T> theModel) {
		for (int i=0; i<size(); i++) {
			if (get(i).getModel().getClass() == theModel)  {
				if (get(i).getModel().getId() == (uid)) {
					return get(i);
				}
			}
		}
		
		return null;
	}
}
