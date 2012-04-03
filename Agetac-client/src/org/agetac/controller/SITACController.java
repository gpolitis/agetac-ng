package org.agetac.controller;

import org.agetac.activity.ITabActivity;
import org.agetac.command.AddEntityCommand;
import org.agetac.command.RemoveEntityCommand;

public class SITACController implements ISubController {
	
	private static final String TAG = "SITACController";
	
	private Controller parent;
	
	public SITACController(Controller controller) {
		parent = controller;
	}

	@Override
	public void processUpdate(ITabActivity act) {
		switch (act.getActionFlag()) {
		
		case ADD:
			parent.setLastEntity(act.getTouchedEntity());
			parent.getCommands().get(AddEntityCommand.NAME).execute(); 
			break;
		
		case REMOVE:
			parent.setLastEntity(act.getTouchedEntity());
			parent.getCommands().get(RemoveEntityCommand.NAME).execute();
			break;
		
		default : 
			android.util.Log.w(TAG, "FLAG inconnu!");
			break;
		}
	}

}
