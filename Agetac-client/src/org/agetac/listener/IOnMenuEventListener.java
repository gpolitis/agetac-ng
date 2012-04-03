package org.agetac.listener;

import org.agetac.entity.IEntity;
import org.agetac.view.MenuGroup;

public interface IOnMenuEventListener {

	public void onHideMenu();
	
	public void onShowMenu();
	
	public void onEntitySelected(IEntity e, MenuGroup grp);
}
