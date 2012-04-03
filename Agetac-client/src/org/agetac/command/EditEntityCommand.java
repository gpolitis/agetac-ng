package org.agetac.command;

import org.agetac.controller.Controller;
import org.agetac.engine.IInterventionEngine;
import org.agetac.memento.IMemento;

public class EditEntityCommand implements IRecordableCommand {
	
	public static final String NAME = "EditEntity";
	
	private IInterventionEngine engine;
	private Controller controller;
	
	public EditEntityCommand(Controller controller) {
		this.controller = controller;
		this.engine = controller.getInterventionEngine();
	}

	@Override
	public void execute() {
		engine.editEntity(controller.getLastEntity());
	}

	@Override
	public IMemento saveMemento() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void restoreMemento(IMemento mem) {
		// TODO Auto-generated method stub
		
	}

}
