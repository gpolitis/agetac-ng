package org.agetac.activity;

import org.agetac.controller.Controller.ActionFlag;
import org.agetac.entity.IEntity;

public interface ITabActivity {

	public ActionFlag getActionFlag();
	
	public IEntity getTouchedEntity();
	
	public String getMessage();
	
	public void update();
}
