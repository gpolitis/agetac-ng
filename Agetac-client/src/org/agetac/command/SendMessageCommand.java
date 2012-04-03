package org.agetac.command;

import java.util.Date;

import org.agetac.common.dto.MessageDTO;
import org.agetac.controller.Controller;
import org.agetac.memento.IMemento;

public class SendMessageCommand implements IRecordableCommand {
	public static final String NAME = "EnvoyerMessage";
	//private static int idMessage = 0;
	private static boolean messOk = false;



	private Controller controller;

	public SendMessageCommand(Controller controller) {
		this.controller = controller;

	}



	public static boolean getMessOk () {

		return messOk;
	}

	public String getGroupeHoraire(){

		Date d = new Date();
		String res="";

		int minutes = d.getMinutes();
		int heures = d.getHours();

		if(heures < 10 ) {res = "0" + heures;}
		else {res = "" + heures;}

		if (minutes < 10) {res = res +"0" + minutes;}
		else { res = res + minutes;}

		return res;
	}


	public void sendMessage(String message)  {

		
		
		String date = getGroupeHoraire();
		//String id = "" + SendMessageCommand.idMessage;
		MessageDTO mess = new MessageDTO (message, date);
		System.out.println("Message : " + message + "," + date);
		
		
		messOk = controller.getInterventionEngine().sendMessage(mess);
		
		
	}


	public void execute() {

		sendMessage(controller.getMessage());
		//idMessage++;
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