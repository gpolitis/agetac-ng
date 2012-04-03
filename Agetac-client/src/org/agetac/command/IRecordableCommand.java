package org.agetac.command;

import org.agetac.memento.IMemento;

public interface IRecordableCommand extends ICommand {

	public IMemento saveMemento();
	
	public void restoreMemento(IMemento mem);
}
