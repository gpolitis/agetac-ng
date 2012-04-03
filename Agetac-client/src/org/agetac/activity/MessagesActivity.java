package org.agetac.activity;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

import org.agetac.R;
import org.agetac.command.SendMessageCommand;
import org.agetac.common.dto.MessageDTO;
import org.agetac.controller.Controller;
import org.agetac.controller.Controller.ActionFlag;

import android.app.AlertDialog;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleAdapter;

public class MessagesActivity extends AbstractActivity implements OnClickListener {

	private static final String TAG = "MessageActivity";
	private String message;
	private SimpleAdapter listAdapter;
	private ListView listView;
	private List<Hashtable<String, String>> listMessages;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.messages);

		findViewById(R.id.buttonEnvoyer).setOnClickListener(this);
		findViewById(R.id.buttonAnnuler).setOnClickListener(this);
		findViewById(R.id.buttonRetMess).setOnClickListener(this);
		findViewById(R.id.buttonConsMess).setOnClickListener(this);
	}

	@Override
	public void update() {
		
	}

	@Override
	public String getMessage(){
		return message;
	}

	@Override
	public void onClick(View v) {

		switch (v.getId()){

		case R.id.messAnnuler : 
			EditText et = (EditText)findViewById(R.id.edittext_message_normal);
			et.setText("");

			break;

		case R.id.messEnvoyer :

			EditText texteAEnvoyer = (EditText)findViewById(R.id.edittext_message_normal);
			message = texteAEnvoyer.getText().toString();

			flag = ActionFlag.SEND_MESSAGE;

			texteAEnvoyer.setText("");
			observable.setChanged();
			observable.notifyObservers(MessagesActivity.this);

			if(SendMessageCommand.getMessOk()){

				AlertDialog.Builder confirmSend = new AlertDialog.Builder(this);

				confirmSend.setMessage("Message envoye");
				confirmSend.setNeutralButton("OK", null);
				confirmSend.show();
			

			}

			else {
				AlertDialog.Builder confirmSend = new AlertDialog.Builder(this);
				confirmSend.setMessage("Echec de l'envoi");
				confirmSend.setNeutralButton("Ok", null);
				confirmSend.show();
			}
		

		break;


	case R.id.buttonEnvoyer :

		EditText TexteJeSuis = (EditText)findViewById(R.id.edittext_je_suis);
		String jeSuis = TexteJeSuis.getText().toString();
		EditText TexteJeVois = (EditText)findViewById(R.id.edittext_je_vois);
		String jeVois = TexteJeVois.getText().toString();
		EditText TexteJePrevois = (EditText)findViewById(R.id.edittext_je_prevois);
		String jePrevois = TexteJePrevois.getText().toString();
		EditText TexteJeProcede = (EditText)findViewById(R.id.edittext_je_procede);
		String jeProcede = TexteJeProcede.getText().toString();
		EditText TexteJeDemande = (EditText)findViewById(R.id.edittext_je_demande);
		String jeDemande = TexteJeDemande.getText().toString();

		message = "Je suis : " + jeSuis + "\n" + 
				"Je vois : " + jeVois + "\n" +
				"Je prevois : " + jePrevois + "\n" +
				"Je procede : " + jeProcede + "\n" +
				"Je demande : " + jeDemande + "\n";



		flag = ActionFlag.SEND_MESSAGE;

		TexteJeSuis.setText("");
		TexteJeVois.setText("");
		TexteJePrevois.setText("");
		TexteJeProcede.setText("");
		TexteJeDemande.setText("");

		observable.setChanged();
		observable.notifyObservers(MessagesActivity.this);	

		if(SendMessageCommand.getMessOk()){

			AlertDialog.Builder confirmSend = new AlertDialog.Builder(this);

			confirmSend.setMessage("Message envoye");
			confirmSend.setNeutralButton("OK", null);
			confirmSend.show();
			
		}

		else {
			AlertDialog.Builder confirmSend = new AlertDialog.Builder(this);
			confirmSend.setMessage("Echec de l'envoi");
			confirmSend.setNeutralButton("Ok", null);
			confirmSend.show();
		}
	
		break;

	case R.id.buttonAnnuler : 

		EditText jeSuisA = (EditText)findViewById(R.id.edittext_je_suis);
		jeSuisA.setText("");
		EditText jeVoisA = (EditText)findViewById(R.id.edittext_je_vois);
		jeVoisA.setText("");
		EditText jePrevoisA = (EditText)findViewById(R.id.edittext_je_prevois);
		jePrevoisA.setText("");
		EditText jeProcedeA = (EditText)findViewById(R.id.edittext_je_procede);
		jeProcedeA.setText("");
		EditText jeDemandeA = (EditText)findViewById(R.id.edittext_je_demande);
		jeDemandeA.setText("");

		break;

	case R.id.retMessAmb : 

		setContentView(R.layout.messages);
		findViewById(R.id.buttonEnvoyer).setOnClickListener(this);
		findViewById(R.id.buttonAnnuler).setOnClickListener(this);
		findViewById(R.id.buttonRetMess).setOnClickListener(this);
		findViewById(R.id.buttonConsMess).setOnClickListener(this);
		break;


	case R.id.buttonRetMess : 

		setContentView(R.layout.mess);
		findViewById(R.id.messEnvoyer).setOnClickListener(this);
		findViewById(R.id.messAnnuler).setOnClickListener(this);
		findViewById(R.id.retMessAmb).setOnClickListener(this);
		findViewById(R.id.buttonConsMess).setOnClickListener(this);
		break;



	case R.id.buttonConsMess : 

		setContentView(R.layout.reception_mess);
		findViewById(R.id.retMessAmb).setOnClickListener(this);
		findViewById(R.id.buttonRetMess).setOnClickListener(this);
		
		listView = (ListView) findViewById(R.id.listMess);
		listMessages = new ArrayList<Hashtable<String, String>>();
		listAdapter = new SimpleAdapter(this.getBaseContext(), listMessages, R.layout.liste_message,
	               new String[] {"titre", "description"}, new int[] {R.id.titre, R.id.description});
	 
		listView.setAdapter(listAdapter); 
	    listMessages.clear();	
	    
		List<MessageDTO> messagesRecus = controller.getInterventionEngine().getListMessages();
		
	    Hashtable<String, String> map;
	   
	    for(int i=0; i<=messagesRecus.size(); i++) {
			map = new Hashtable<String, String>();	
			map.put("titre", messagesRecus.get(i).getDate().toString());
			map.put("description", messagesRecus.get(i).getText().toString());
			listMessages.add(map);
		}
		
		
	/*	  listView.setOnItemClickListener(new OnItemClickListener() {
			@Override
			@SuppressWarnings("unchecked")
			public void onItemClick(AdapterView<?> a, View v, int position, long id) {
				HashMap<String, String> map2 = (HashMap<String, String>) listView.getItemAtPosition(position);
				AlertDialog.Builder adb = new AlertDialog.Builder(MessagesActivity.this);
				adb.setTitle("Vue du message");
				adb.setMessage(map2.get("description"));
				adb.setPositiveButton("Ok", null);
				adb.show();
			}		
			}); */
	
		break;

	}
}

}






